package com.deltav.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitMqTest {
    public static final String EXCHANGE_CLUSTER = "exchange.cluster.test";
    public static final String ROUTING_KEY_CLASSIC = "routing.key.cluster.test";
    public static final String ROUTING_KEY_QUORUM = "routing.key.quorum";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplateWithTx;

    @Test
    void testSendMessage() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_CLUSTER,
                ROUTING_KEY_CLASSIC,
                "Cluster Test Message");
    }

    @Test
    void testSendMessageToQuorumQueue() {
        rabbitTemplate.convertAndSend(
                EXCHANGE_CLUSTER,
                ROUTING_KEY_QUORUM,
                "Cluster Test Message for Quorum");
    }

}
