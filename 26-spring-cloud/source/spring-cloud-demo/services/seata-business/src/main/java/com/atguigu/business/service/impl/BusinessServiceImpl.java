package com.atguigu.business.service.impl;

import com.atguigu.business.feign.OrderFeignClient;
import com.atguigu.business.feign.StorageFeignClient;
import com.atguigu.business.service.BusinessService;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BusinessServiceImpl implements BusinessService {
    private final StorageFeignClient storageFeignClient;
    private final OrderFeignClient orderFeignClient;

    @Autowired
    public BusinessServiceImpl(StorageFeignClient storageFeignClient, OrderFeignClient orderFeignClient) {
        this.storageFeignClient = storageFeignClient;
        this.orderFeignClient = orderFeignClient;
    }

    @GlobalTransactional
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {
        // 1. 扣减库存
        storageFeignClient.deduct(commodityCode, orderCount);
        // 2. 创建订单
        orderFeignClient.create(userId, commodityCode, orderCount);
    }
}
