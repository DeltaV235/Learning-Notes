package com.atguigu.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-storage")
public interface StorageFeignClient {

    /**
     * Deducts a specified amount of a commodity from storage.
     *
     * @param commodityCode the code of the commodity to be deducted
     * @param count         the quantity to be deducted
     * @return a message indicating the result of the deduction operation
     */
    @GetMapping("/deduct")
    String deduct(@RequestParam("commodityCode") String commodityCode,
                  @RequestParam("count") Integer count);
}
