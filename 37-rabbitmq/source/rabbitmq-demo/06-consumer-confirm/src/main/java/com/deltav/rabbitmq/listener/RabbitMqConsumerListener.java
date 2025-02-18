package com.deltav.rabbitmq.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

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

//    @RabbitListener(queues = {QUEUE_NAME})
//    public void processMessage(String msgData, Message message, Channel channel) throws IOException {
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        try {
//            log.info(msgData);
//            int i = 10 / 0;
//            channel.basicAck(deliveryTag, false);
//        } catch (Exception e) {
//            boolean requeue = !message.getMessageProperties().getRedelivered();
//            channel.basicNack(deliveryTag, false, requeue);
//        }
//    }

    @RabbitListener(queues = {QUEUE_NAME}, concurrency = "10")
    public void processMessage(String msgData, Message message, Channel channel) throws IOException, InterruptedException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info(msgData);
        TimeUnit.SECONDS.sleep(1);
        channel.basicAck(deliveryTag, false);
    }

    public static final String QUEUE_NORMAL = "queue.normal.video";
    public static final String QUEUE_DEAD_LETTER = "queue.dead.letter.video";

    @RabbitListener(queues = {QUEUE_NORMAL})
    public void processMessageNormal(Message message, Channel channel) throws IOException {
        // 监听正常队列，但是拒绝消息
        log.info("★[normal]消息接收到，但我拒绝。");
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = {QUEUE_DEAD_LETTER})
    public void processMessageDead(String dataString, Message message, Channel channel) throws IOException {
        // 监听死信队列
        log.info("★[dead letter]dataString = " + dataString);
        log.info("★[dead letter]我是死信监听方法，我接收到了死信消息");
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "queue.delay")
    public void processMessageDelay(String dataString, Message message, Channel channel) throws IOException {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);

        log.info("Current Time: {} - {}", formattedTime, dataString);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "queue.priority")
    public void processMessagePriority(String dataString, Message message, Channel channel) throws IOException {
        log.info("{}", dataString);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
