package pl.lodz.p.it;

import com.rabbitmq.client.*;
import lombok.extern.java.Log;
import pl.lodz.p.it.Services.UserService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Log
@ApplicationScoped
public class ReceiverRPC {

    @Inject
    private UserService userService;

    private static final String HOST_NAME = "localhost";
    private static final String RPC_QUEUE = "rpc_queue";

    private ConnectionFactory connectionFactory;
    private Connection connection;
    private Channel channel;

    @PostConstruct
    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        try {
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(HOST_NAME);
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(RPC_QUEUE, true, false, false, null);
            channel.queuePurge(RPC_QUEUE);
            channel.basicQos(1);
            waitForRequests();
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

    private void waitForRequests() throws IOException {
        System.out.println("Waiting for rpc requests");

        Object monitor = new Object();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            AMQP.BasicProperties properties = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();

            String response = "";
            try {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                User user = userService.getUser(message);
                response = Json.createObjectBuilder()
                        .add("login", user.getLogin())
                        .add("password", user.getPassword())
                        .add("name", user.getName())
                        .add("surname", user.getSurname())
                        .add("isActive", user.getIsActive())
                        .add("group", user.getGroup().toString()).build().toString();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                channel.basicPublish("", delivery.getProperties().getReplyTo(), properties, response.getBytes(StandardCharsets.UTF_8));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                synchronized (monitor) {
                    monitor.notify();
                }
            }
        };

        channel.basicConsume(RPC_QUEUE, false, deliverCallback, (consumerTag -> {}));

//        while (true) {
//            synchronized (monitor) {
//                try {
//                    monitor.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    private JsonArrayBuilder getUsers() {
        Map<String, User> map = userService.getUsers();
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for(User u: map.values()) {
            String json = Json.createObjectBuilder()
                    .add("login", u.getLogin())
                    .add("password", u.getPassword())
                    .add("name", u.getName())
                    .add("surname", u.getSurname())
                    .add("isActive", u.getIsActive())
                    .add("group", u.getGroup().toString()).build().toString();
            jsonArray.add(json);
        }
        return jsonArray;
    }
}
