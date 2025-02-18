package com.deltav.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class RabbitMqTest {
    public static final String EXCHANGE_DIRECT = "exchange.direct.order";
    public static final String ROUTING_KEY = "order";
    public static final String EXCHANGE_DIRECT_TIMEOUT = "exchange.direct.timeout";
    public static final String ROUTING_KEY_TIMEOUT = "timeout";
    private static final String EXCHANGE_DIRECT_DELAY = "exchange.direct.delay";
    private static final String ROUTING_KEY_DELAY = "delay";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplateWithTx;

    @Test
    void testSendMessage() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT,
                ROUTING_KEY,
                "Hello World!");
    }

    @Test
    void testSendMessage02() {
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_DIRECT,
                    ROUTING_KEY,
                    "Hello World - " + i);
        }
    }

    @Test
    void testSendMessage03() {
        for (int i = 0; i < 1000; i++) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_DIRECT_TIMEOUT,
                    ROUTING_KEY_TIMEOUT,
                    "Hello World - " + i);
        }
    }

    @Test
    void testSendMessage04() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT_TIMEOUT,
                ROUTING_KEY_TIMEOUT,
                "Hello World",
                message -> {
                    message.getMessageProperties().setExpiration("7000");
                    return message;
                });
    }

    public static final String EXCHANGE_NORMAL = "exchange.normal.video";
    public static final String ROUTING_KEY_NORMAL = "routing.key.normal.video";

    @Test
    public void testSendMultiMessage() {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend(
                    EXCHANGE_NORMAL,
                    ROUTING_KEY_NORMAL,
                    "测试死信情况2：消息数量超过队列的最大容量" + i);
        }
    }

    @Test
    void testSendMessage05() {
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        rabbitTemplate.convertAndSend(
                EXCHANGE_DIRECT_DELAY,
                ROUTING_KEY_DELAY,
                "Hello World " + currentTime,
                message -> {
                    message.getMessageProperties().setHeader("x-delay", "20000");
                    return message;
                });
    }

    @Test
    @Transactional
    @Rollback
    void testSendMessage06Transaction() {
        rabbitTemplateWithTx.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY, "Transaction of producer - 01");
        int i = 10 / 0;
        rabbitTemplateWithTx.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY, "Transaction of producer - 02");
    }

    @Test
    void testSendMessage07() {
        String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        rabbitTemplate.convertAndSend(
                "exchange.direct.priority",
                "priority",
                "priority message - " + currentTime,
                message -> {
                    message.getMessageProperties().setPriority(3);
                    return message;
                });
    }
}
