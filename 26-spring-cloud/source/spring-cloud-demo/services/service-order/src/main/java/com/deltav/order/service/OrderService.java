package com.deltav.order.service;

import com.deltav.order.bean.Order;

public interface OrderService {
    Order create(Long productId, Long userId);
}
