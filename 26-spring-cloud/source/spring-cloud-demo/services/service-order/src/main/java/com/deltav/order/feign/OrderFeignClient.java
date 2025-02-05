package com.deltav.order.feign;

import com.deltav.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

@FeignClient(value = "service-product", fallback = OrderFeignClient.OrderFeignClientFallback.class)
public interface OrderFeignClient {

    @GetMapping("/{id}")
    Product getProduct(@PathVariable("id") Long productId);

    @Component
    @Slf4j
    class OrderFeignClientFallback implements OrderFeignClient {
        @Override
        public Product getProduct(Long productId) {
            log.error("Fallback data returned!");
            return Product.builder()
                    .id(productId)
                    .productName("Unknown Product")
                    .price(BigDecimal.ZERO)
                    .num(0L)
                    .build();
        }
    }
}
