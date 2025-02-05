package com.atguigu.order.service;

import com.atguigu.order.bean.OrderTbl;

public interface OrderService {
    /**
     * 创建订单
     * @param userId    用户id
     * @param commodityCode  商品编码
     * @param orderCount  商品数量
     */
    OrderTbl create(String userId, String commodityCode, int orderCount);
}
