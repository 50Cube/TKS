package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequestScoped
public class Publisher {

    private final String HOST_NAME = "localhost";
    private final String EXCHANGE_NAME = "exchange_topic";
    private final String EXCHANGE_TYPE = "topic";

    private final String CREATE_USER_KEY = "user.create";
    private final String UPDATE_USER_KEY = "user.update";

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    @PostConstruct
    public void init() {
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_NAME);
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUser(String json) throws IOException {
        channel.basicPublish(EXCHANGE_NAME, CREATE_USER_KEY ,null, json.getBytes(StandardCharsets.UTF_8));
    }

    public void updateUser(String json) throws IOException {
        channel.basicPublish(EXCHANGE_NAME, UPDATE_USER_KEY, null, json.getBytes(StandardCharsets.UTF_8));
    }
}
