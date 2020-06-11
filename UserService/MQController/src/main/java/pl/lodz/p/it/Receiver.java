package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Startup
@Singleton
@Log
public class Receiver {

    Properties properties = new Properties();
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    @SneakyThrows
    @PostConstruct
    public void init() {
        properties.load(new FileInputStream("MQController/src/main/resources/rabbit.properties"));
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(properties.getProperty("host_name"));

        connection = connectionFactory.newConnection();
        channel = connection.createChannel();
        channel.exchangeDeclare(properties.getProperty("exchange_name"), properties.getProperty("exchange_type"));
        createUser();
    }

    public void createUser() throws IOException {
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, properties.getProperty("exchange_name"), "user.create");


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(delivery.getEnvelope().getRoutingKey() + " " + message);
            log.info("User create request " + message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }

}
