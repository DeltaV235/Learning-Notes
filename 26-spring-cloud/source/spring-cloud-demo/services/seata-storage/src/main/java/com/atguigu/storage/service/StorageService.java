package com.atguigu.storage.service;

public interface StorageService {


    /**
     * 扣除存储数量
     * @param commodityCode 商品编码
     * @param count 数量
     */
    void deduct(String commodityCode, int count);
}
