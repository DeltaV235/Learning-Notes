package com.deltav.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class RabbitMqConsumerListener {
    private static final String QUEUE_NAME_CLASSIC = "queue.cluster.test";
    private static final String QUEUE_NAME_QUORUM = "queue.quorum";

    @RabbitListener(queues = {QUEUE_NAME_CLASSIC, QUEUE_NAME_QUORUM}, concurrency = "10")
    public void processMessage(String msgData, Message message, Channel channel) throws IOException, InterruptedException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info(msgData);
        channel.basicAck(deliveryTag, false);
    }

}
