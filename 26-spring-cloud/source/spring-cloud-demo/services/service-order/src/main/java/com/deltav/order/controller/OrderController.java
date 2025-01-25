package com.deltav.order.controller;

import com.deltav.order.bean.Order;
import com.deltav.order.properties.OrderProperties;
import com.deltav.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

    @GetMapping("/order/create")
    public Order createOrder(@RequestParam Long productId,
                             @RequestParam Long userId) {
        return orderService.create(productId, userId);
    }
}
