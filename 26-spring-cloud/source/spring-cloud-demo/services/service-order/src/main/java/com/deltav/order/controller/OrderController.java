package com.deltav.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.deltav.order.bean.Order;
import com.deltav.order.properties.OrderProperties;
import com.deltav.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
//@RequestMapping("/api/order")
//@RefreshScope
public class OrderController {
    private final OrderService orderService;

//    @Value("${order.timeout}")
//    private String timeout;
//
//    @Value("${order.auto-confirm}")
//    private String autoConfirm;

    private final OrderProperties orderProperties;

    @Autowired
    public OrderController(OrderService orderService, OrderProperties orderProperties) {
        this.orderService = orderService;
        this.orderProperties = orderProperties;
    }

    @GetMapping("/config")
    public String getConfig() {
        return String.format("timeout: %s, autoConfirm: %s, databaseUrl: %s",
                orderProperties.getTimeout(), orderProperties.getAutoConfirm(), orderProperties.getDatabaseUrl());
    }

    @GetMapping("/create")
    @SentinelResource(value = "createOrder-api", fallback = "createOrderFallback")
    public Order createOrder(@RequestParam Long productId,
                             @RequestParam Long userId) {
        return orderService.create(productId, userId);
    }

    public Order createOrderFallback(Long productId,
                                     Long userId,
                                     Throwable blockException) {
        log.error("Fallback data returned!");
        return Order.builder()
                .id(0L)
                .userId(userId)
                .address("Unknown Address " + blockException.getClass().getSimpleName())
                .totalAmount(BigDecimal.ZERO)
                .nickName("Unknown NickName " + blockException.getMessage())
                .build();
    }

    @GetMapping("/seckill")
    public Order seckill(@RequestParam Long productId,
                         @RequestParam Long userId) {
        return orderService.create(productId, userId);
    }
}
