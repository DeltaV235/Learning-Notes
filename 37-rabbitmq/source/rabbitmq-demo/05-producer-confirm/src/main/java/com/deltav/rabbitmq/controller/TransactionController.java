package com.deltav.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    private static final String EXCHANGE_DIRECT = "exchange.direct.order";
    private static final String ROUTING_KEY = "order";

    private final RabbitTemplate rabbitTemplateWithTx;

    @Autowired
    public TransactionController(@Qualifier("rabbitTemplateWithTx") RabbitTemplate rabbitTemplateTx) {
        this.rabbitTemplateWithTx = rabbitTemplateTx;
    }

    @GetMapping("/test/message/transaction")
//    @Transactional
    public String testMessageTransaction() {
        rabbitTemplateWithTx.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY, "Transaction of producer - 01");
        int i = 10 / 0;
        rabbitTemplateWithTx.convertAndSend(EXCHANGE_DIRECT, ROUTING_KEY, "Transaction of producer - 02");
        return "test";
    }
}
