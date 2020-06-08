package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.annotation.PostConstruct;
import java.util.Properties;

public class Receiver {

    Properties properties = new Properties();
    private ConnectionFactory connectionFactory;

    @PostConstruct
    public void init() throws Exception {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(properties.getProperty("host_name"));
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(properties.getProperty("exchange_name"), properties.getProperty("exchange_type"));
        String queueName = channel.queueDeclare().getQueue();
    }
}
