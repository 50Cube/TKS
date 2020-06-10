package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

@Log
@Startup
@ApplicationScoped
public class Receiver {

    Properties properties = new Properties();
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    @PostConstruct
    public void init()  {
        connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost(properties.getProperty("host_name"));
        connectionFactory.setHost("localhost");
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
//            channel.exchangeDeclare(properties.getProperty("exchange_name"), properties.getProperty("exchange_type"));
            channel.exchangeDeclare("topic_exchange", "topic");
            createUser();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void createUser() throws IOException {
        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName, properties.getProperty("exchange_name"), "user.create");
        channel.queueBind(queueName, "topic_exchange", "user.create");


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(delivery.getEnvelope().getRoutingKey() + " " + message);
            log.info("HERB " + message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }
}
