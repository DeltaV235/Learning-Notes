package com.deltav.order.controller;

import com.deltav.order.bean.Order;
import com.deltav.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/create")
    public Order createOrder(@RequestParam Long productId,
                             @RequestParam Long userId) {
        return orderService.create(productId, userId);
    }
}
