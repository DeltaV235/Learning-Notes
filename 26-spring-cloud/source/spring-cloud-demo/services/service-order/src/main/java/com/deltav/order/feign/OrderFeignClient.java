package com.deltav.order.feign;

import com.deltav.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-product")
public interface OrderFeignClient {

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable("id") Long productId);
}
