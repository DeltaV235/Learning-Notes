package com.deltav.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMqConsumerListener {
    private static final String EXCHANGE_DIRECT = "exchange.direct.order";
    private static final String ROUTING_KEY = "order";
    private static final String QUEUE_NAME = "queue.order";

    //    @RabbitListener(
//            bindings = @QueueBinding(
//                    value = @Queue(value = QUEUE_NAME, durable = "true"),
//                    exchange = @Exchange(value = EXCHANGE_DIRECT),
//                    key = {ROUTING_KEY}
//            )
//    )
    @RabbitListener(queues = {QUEUE_NAME})
    public void processMessage(String msgData, Message message, Channel channel) {
        log.info(msgData);
    }
}
