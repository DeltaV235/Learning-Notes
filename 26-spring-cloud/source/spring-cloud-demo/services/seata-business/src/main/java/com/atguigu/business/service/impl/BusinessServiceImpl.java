package com.atguigu.business.service.impl;

import com.atguigu.business.service.BusinessService;
import org.springframework.stereotype.Service;


@Service
public class BusinessServiceImpl implements BusinessService {
    @Override
    public void purchase(String userId, String commodityCode, int orderCount) {
        //TODO 1. 扣减库存

        //TODO 2. 创建订单
    }
}
