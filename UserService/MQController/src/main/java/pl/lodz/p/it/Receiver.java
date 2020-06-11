package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.java.Log;
//import pl.lodz.p.it.Services.UserService;

import javax.annotation.PostConstruct;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

@Log
@Startup
@Singleton
public class Receiver {
//
//    @Inject
//    private UserService userService;

    private final String HOST_NAME = "localhost";
    private final String EXCHANGE_NAME = "exchange_topic";
    private final String EXCHANGE_TYPE = "topic";

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;
    private String queueName;

    @PostConstruct
    public void init() {
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_NAME);
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            queueName = channel.queueDeclare().getQueue();
            bindKeys();
            getMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindKeys() throws IOException {
        channel.queueBind(queueName, EXCHANGE_NAME, "user.create");
        channel.queueBind(queueName, EXCHANGE_NAME, "user.update");
    }

    private void getMessage() throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            switch (delivery.getEnvelope().getRoutingKey()) {
                case "user.create": {
                    createUser(new String(delivery.getBody(), StandardCharsets.UTF_8));
                    break;
                }
                case "user-update": {
                    updateUser(new String(delivery.getBody(), StandardCharsets.UTF_8));
                    break;
                }
                default: {
                    break;
                }
            }
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }

    private void createUser(String message) {
        JsonReader reader = Json.createReader(new StringReader(message));
        JsonObject jsonObject = reader.readObject();
        log.info("Method createUser invoked with parameter: " + message);
//        userService.addUser(
//                jsonObject.getString("userType"),
//                jsonObject.getString("login"),
//                jsonObject.getString("name"),
//                jsonObject.getString("surname"),
//                jsonObject.getBoolean("isActive"),
//                jsonObject.getString("password"));
//        log.info("USERS = " + userService.getUsers().values());
    }

    private void updateUser(String message) {

    }

}
