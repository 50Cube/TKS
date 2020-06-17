package pl.lodz.p.it;

import com.rabbitmq.client.*;
import lombok.extern.java.Log;
import pl.lodz.p.it.Services.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Log
@ApplicationScoped
public class ReceiverRPC {

    @Inject
    private UserService userService;

    private static final String HOST_NAME = "localhost";
    private static final String RPC_QUEUE = "rpc_queue";

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    @PostConstruct
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_NAME);
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(RPC_QUEUE, true, false, false, null);
            channel.queuePurge(RPC_QUEUE);
            channel.basicQos(1);
            waitForRequests();
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

    private void waitForRequests() throws IOException {
        Object monitor = new Object();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            AMQP.BasicProperties properties = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();

            String response = "";
            try {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                if(message.contains("getAll")) {
                    response = getUsers();
                } else if (message.contains("getOne")) {
                    response = getUser(message.substring(message.indexOf('.') + 1));
                } else if (message.contains("getFiltered")) {
                    response = getFilteredUsers(message.substring(message.indexOf('.') + 1));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                channel.basicPublish("", delivery.getProperties().getReplyTo(), properties, response.getBytes(StandardCharsets.UTF_8));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                synchronized (monitor) {
                    monitor.notify();
                }
            }
        };
        channel.basicConsume(RPC_QUEUE, false, deliverCallback, (consumerTag -> {}));
    }

    private String prepareJsonObject(User user) {
        return Json.createObjectBuilder()
                .add("login", user.getLogin())
                .add("password", user.getPassword())
                .add("name", user.getName())
                .add("surname", user.getSurname())
                .add("isActive", user.getIsActive())
                .add("group", user.getGroup().toString()).build().toString();
    }

    private String getUser(String message) {
        User user = userService.getUser(message);
        return prepareJsonObject(user);
    }

    private String getUsers() {
        Map<String, User> map = userService.getUsers();
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for(User user: map.values())
            jsonArray.add(prepareJsonObject(user));
        return jsonArray.build().toString();
    }

    private String getFilteredUsers(String filter) {
        Map<String, User> map = userService.getFilterUsers(filter);
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for(User user: map.values())
            jsonArray.add(prepareJsonObject(user));
        return jsonArray.build().toString();
    }
}
