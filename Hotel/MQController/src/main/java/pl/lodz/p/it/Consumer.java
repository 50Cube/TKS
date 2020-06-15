package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import pl.lodz.p.it.UIPorts.Aggregates.UserAdapterUI;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

@ApplicationScoped
public class Consumer {

    @Inject
    private UserAdapterUI userService;

    private static final String HOST_NAME = "localhost";
    private static final String EXCHANGE_NAME = "exchange_topic";
    private static final String EXCHANGE_TYPE = "topic";

    private static final String CREATE_USER_KEY = "user.create";
    private static final String UPDATE_USER_KEY = "user.update";

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;
    private String queueName;

    @PostConstruct
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_NAME);
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            channel.basicQos(1);
            queueName = channel.queueDeclare().getQueue();
            bindKeys();
            getMessage();
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

    private void bindKeys() throws IOException {
        channel.queueBind(queueName, EXCHANGE_NAME, CREATE_USER_KEY);
        channel.queueBind(queueName, EXCHANGE_NAME, UPDATE_USER_KEY);
    }

    private void getMessage() throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            switch (delivery.getEnvelope().getRoutingKey()) {
                case CREATE_USER_KEY: {
                    try {
                        createUser(new String(delivery.getBody(), StandardCharsets.UTF_8));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case UPDATE_USER_KEY: {
                    updateUser(new String(delivery.getBody(), StandardCharsets.UTF_8));
                    break;
                }
                default: {
                    break;
                }
            }
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
    }

    private void createUser(String message) throws Exception {
        JsonReader reader = Json.createReader(new StringReader(message));
        JsonObject jsonObject = reader.readObject();
        if(jsonObject.getString("userType").equalsIgnoreCase("admin")) {
            userService.addAdmin(
                    jsonObject.getString("login"),
                    jsonObject.getString("name"),
                    jsonObject.getString("surname"),
                    jsonObject.getBoolean("isActive"));
        } else if(jsonObject.getString("userType").equalsIgnoreCase("manager")) {
            userService.addManager(
                    jsonObject.getString("login"),
                    jsonObject.getString("name"),
                    jsonObject.getString("surname"),
                    jsonObject.getBoolean("isActive"));
        } else if(jsonObject.getString("userType").equalsIgnoreCase("client")) {
            userService.addClient(
                    jsonObject.getString("login"),
                    jsonObject.getString("name"),
                    jsonObject.getString("surname"),
                    jsonObject.getBoolean("isActive"));
        }
    }

    private void updateUser(String message) {
        JsonReader reader = Json.createReader(new StringReader(message));
        JsonObject jsonObject = reader.readObject();
        userService.updateUser(
                jsonObject.getString("login"),
                jsonObject.getString("name"),
                jsonObject.getString("surname")
        );
    }
}
