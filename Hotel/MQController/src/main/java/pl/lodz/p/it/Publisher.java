package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

@RequestScoped
public class Publisher {

    Properties properties = new Properties();
    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    @PostConstruct
    public void init() {
        connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost(properties.getProperty("host_name"));
        connectionFactory.setHost("localhost");


        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare("topic_exchange", "topic");
//            channel.exchangeDeclare(properties.getProperty("exchange_name"), properties.getProperty("exchange_type"));
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String role, String login, String name, String surname, boolean isActive, String password) throws IOException {
//        channel.basicPublish(properties.getProperty("exchange_name"), "user.create" ,null, message.getBytes(StandardCharsets.UTF_8));
        StringBuilder object = new StringBuilder();
        object.append(role).append(";").append(login).append(";").append(name).append(";")
                .append(surname).append(";").append(isActive).append(";").append(password).append(";");
        channel.basicPublish("topic_exchange", "user.create" ,null, object.toString().getBytes(StandardCharsets.UTF_8));
    }
}
