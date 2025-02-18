package com.deltav.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    private static final String QUEUE_NAME = "simple_queue";
    private static final String HOST = "192.168.0.31";
    private static final int PORT = 5672;
    private static final String VIRTUAL_HOST = "/";
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        try (Connection connection = createConnection();
             Channel channel = connection.createChannel()) {

            // 创建队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            // 接收消息
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    System.out.println("consumerTag：" + consumerTag);
                    System.out.println("Exchange：" + envelope.getExchange());
                    System.out.println("RoutingKey：" + envelope.getRoutingKey());
                    System.out.println("properties：" + properties);
                    System.out.println("body：" + new String(body));
                }
            };

            channel.basicConsume(QUEUE_NAME, true, consumer);
        }
    }

    private static Connection createConnection() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setPort(PORT);
        factory.setVirtualHost(VIRTUAL_HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        return factory.newConnection();
    }
}