package pl.lodz.p.it;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.java.Log;
import pl.lodz.p.it.UIModel.AdminUI;
import pl.lodz.p.it.UIModel.ClientUI;
import pl.lodz.p.it.UIModel.ManagerUI;
import pl.lodz.p.it.UIModel.UserUI;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.json.*;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Log
@RequestScoped
public class Publisher {

    private static final String HOST_NAME = "localhost";
    private static final String EXCHANGE_NAME = "exchange_topic";
    private static final String EXCHANGE_TYPE = "topic";
    private static final String RPC_QUEUE = "rpc_queue";

    private static final String CREATE_USER_KEY = "user.create";
    private static final String UPDATE_USER_KEY = "user.update";

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    private String correlationID;
    private String replyQueueName;
    private AMQP.BasicProperties properties;

    @PostConstruct
    public void init() {
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_NAME);
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void closeConnection() {
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String json) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
        channel.basicPublish(EXCHANGE_NAME, CREATE_USER_KEY,null, json.getBytes(StandardCharsets.UTF_8));
    }

    public void updateUser(String json) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
        channel.basicPublish(EXCHANGE_NAME, UPDATE_USER_KEY, null, json.getBytes(StandardCharsets.UTF_8));
    }

    private void prepareGetMethod(String message) throws IOException {
        correlationID = UUID.randomUUID().toString();
        replyQueueName = channel.queueDeclare().getQueue();
        properties = new AMQP.BasicProperties
                .Builder()
                .deliveryMode(2)
                .correlationId(correlationID)
                .replyTo(replyQueueName)
                .build();
        channel.basicPublish("", RPC_QUEUE, properties, message.getBytes(StandardCharsets.UTF_8));
    }

    public Map<String, UserUI> getUsers(String message) throws IOException, InterruptedException {
        prepareGetMethod(message);
        final BlockingQueue<Map<String, UserUI>> response = new ArrayBlockingQueue<>(1);
        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if(delivery.getProperties().getCorrelationId().equals(correlationID)) {
                response.offer(prepareUserMap(delivery.getBody()));
            }
        }, consumerTag -> {});
        Map<String, UserUI> result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    public UserUI getUser(String message) throws IOException, InterruptedException {
        prepareGetMethod(message);
        final BlockingQueue<UserUI> response = new ArrayBlockingQueue<>(1);

        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if(delivery.getProperties().getCorrelationId().equals(correlationID)) {
                response.offer(Objects.requireNonNull(prepareUser(new String(delivery.getBody()))));
            }
        }, consumerTag -> {});
        UserUI result = response.take();
        channel.basicCancel(ctag);
        return result;
    }

    private UserUI prepareUser(String message) {
        JsonReader reader = Json.createReader(new StringReader(message));
        JsonObject jsonObject = reader.readObject();

        if(jsonObject.getString("group").equalsIgnoreCase("client")) {
            return new ClientUI(jsonObject.getString("login"),
                    jsonObject.getString("password"),
                    jsonObject.getString("name"),
                    jsonObject.getString("surname"),
                    jsonObject.getBoolean("isActive"));
        } else if (jsonObject.getString("group").equalsIgnoreCase("manager")) {
            return new ManagerUI(jsonObject.getString("login"),
                    jsonObject.getString("password"),
                    jsonObject.getString("name"),
                    jsonObject.getString("surname"),
                    jsonObject.getBoolean("isActive"));
        } else if (jsonObject.getString("group").equalsIgnoreCase("admin")) {
            return new AdminUI(jsonObject.getString("login"),
                    jsonObject.getString("password"),
                    jsonObject.getString("name"),
                    jsonObject.getString("surname"),
                    jsonObject.getBoolean("isActive"));
        }
        return null;
    }

    private Map<String, UserUI> prepareUserMap(byte[] bytes) {
        Map<String, UserUI> map = new HashMap<>();
        String message = new String(bytes);
        message = message.replace("\\", "").replace("\"{\"", "{\"").replace("\"}\"", "\"}");

        JsonReader reader = Json.createReader(new StringReader(message));
        JsonArray jsonArray = reader.readArray();

        for(JsonValue jsonValue : jsonArray) {
            JsonObject jsonObject = jsonValue.asJsonObject();
            map.put(jsonObject.getString("login"), prepareUser(jsonObject.toString()));
        }
        return map;
    }
}
