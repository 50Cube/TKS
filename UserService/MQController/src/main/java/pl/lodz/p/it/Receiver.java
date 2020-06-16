package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.java.Log;
import pl.lodz.p.it.Services.UserService;

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

@Log
@ApplicationScoped
public class Receiver {

    @Inject
    private UserService userService;

    private static final String HOST_NAME = "localhost";
    private static final String EXCHANGE_NAME = "exchange_topic";
    private static final String EXCHANGE_TYPE = "topic";

    private static final String CREATE_USER_KEY = "user.create";
    private static final String UPDATE_USER_KEY = "user.update";
    private static final String REMOVE_USER_KEY = "user.remove";

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
                    createUser(new String(delivery.getBody(), StandardCharsets.UTF_8));
                    break;
                }
                case UPDATE_USER_KEY: {
                    updateUser(new String(delivery.getBody(), StandardCharsets.UTF_8));
                    break;
                }
                case REMOVE_USER_KEY: {
                    removeUser(new String(delivery.getBody(), StandardCharsets.UTF_8));
                }
                default: {
                    break;
                }
            }
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {});
    }

    private void createUser(String message) {
        JsonReader reader = Json.createReader(new StringReader(message));
        JsonObject jsonObject = reader.readObject();
        log.info("Method createUser invoked with parameter: " + message);
        userService.addUser(
                jsonObject.getString("userType"),
                jsonObject.getString("login"),
                jsonObject.getString("name"),
                jsonObject.getString("surname"),
                jsonObject.getBoolean("isActive"),
                jsonObject.getString("password"));
    }

    private void updateUser(String message) {
        JsonReader reader = Json.createReader(new StringReader(message));
        JsonObject jsonObject = reader.readObject();
        log.info("Method updateUser invoked with parameter: " + message);
        userService.updateUser(
                jsonObject.getString("login"),
                jsonObject.getString("password"),
                jsonObject.getString("name"),
                jsonObject.getString("surname")
        );
    }

    private void removeUser(String message) {
        userService.removeUser(message);
    }
}
