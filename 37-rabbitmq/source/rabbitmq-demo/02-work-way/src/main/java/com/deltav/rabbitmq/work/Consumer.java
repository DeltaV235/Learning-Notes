package com.deltav.rabbitmq.work;

import com.deltav.rabbitmq.util.RabbitMqUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    static final String QUEUE_NAME = "work_queue";

    public static void main(String[] args) throws Exception {

        Connection connection = RabbitMqUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        com.rabbitmq.client.Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("Consumer body：" + new String(body));

            }

        };

        channel.basicConsume(QUEUE_NAME, true, consumer);

    }
}
