package com.atguigu.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-order")
public interface OrderFeignClient {
    /**
     * Creates an order with the specified user ID, commodity code, and order quantity.
     *
     * @param userId the ID of the user placing the order
     * @param commodityCode the code of the commodity being ordered
     * @param orderCount the quantity of the commodity to be ordered
     * @return a message indicating the result of the order creation operation
     */
    @GetMapping("/create")
    String create(@RequestParam("userId") String userId,
                  @RequestParam("commodityCode") String commodityCode,
                  @RequestParam("count") int orderCount);
}
