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
//        StringBuilder object = new StringBuilder();
//        object.append(role).append(";").append(login).append(";").append(name).append(";")
//                .append(surname).append(";").append(isActive).append(";").append(password).append(";");
        channel.basicPublish(EXCHANGE_NAME, "user.create" ,null, json.getBytes(StandardCharsets.UTF_8));
    }
}
