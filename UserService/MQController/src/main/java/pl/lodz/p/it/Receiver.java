package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Startup
@Singleton
@Log
public class Receiver {

    private final String HOST_NAME = "localhost";
    private final String EXCHANGE_NAME = "exchange_topic";
    private final String EXCHANGE_TYPE = "topic";

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
            createUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createUser() throws IOException {
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "user.create");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(delivery.getEnvelope().getRoutingKey() + " " + message);
            log.info("User create request " + message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }

}
