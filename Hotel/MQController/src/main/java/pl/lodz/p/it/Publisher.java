package pl.lodz.p.it;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@RequestScoped
public class Publisher {

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
    }

    public void createUser(String role, String login, String name, String surname, boolean isActive, String password) throws IOException {
        StringBuilder object = new StringBuilder();
        object.append(role).append(";").append(login).append(";").append(name).append(";")
                .append(surname).append(";").append(isActive).append(";").append(password).append(";");
        channel.basicPublish(properties.getProperty("exchange_name"), "user.create" ,null, object.toString().getBytes(StandardCharsets.UTF_8));
    }
}
