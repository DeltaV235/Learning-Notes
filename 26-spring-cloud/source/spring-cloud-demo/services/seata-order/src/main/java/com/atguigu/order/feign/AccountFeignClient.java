package com.atguigu.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-account")
public interface AccountFeignClient {
    /**
     * Deducts a specified amount of money from a user's account.
     *
     * @param userId the unique identifier of the user whose account will be debited
     * @param money  the amount of money to be deducted from the user's account
     * @return a confirmation message or status indicating the result of the debit operation
     */
    @GetMapping("/debit")
    String debit(@RequestParam("userId") String userId,
                 @RequestParam("money") int money);
}
