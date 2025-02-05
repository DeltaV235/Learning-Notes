package com.atguigu.business.service;

public interface BusinessService {

    /**
     * 采购
     * @param userId            用户id
     * @param commodityCode     商品编号
     * @param orderCount        购买数量
     */
    void purchase(String userId, String commodityCode, int orderCount);
}
