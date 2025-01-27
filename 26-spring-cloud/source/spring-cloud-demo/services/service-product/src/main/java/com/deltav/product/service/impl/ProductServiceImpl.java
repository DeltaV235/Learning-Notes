package com.deltav.product.service.impl;

import com.deltav.product.bean.Product;
import com.deltav.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProduct(Long productId) {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Product.builder()
                .id(productId)
                .productName("Dummy Product-" + productId)
                .price(BigDecimal.valueOf(99.99))
                .num(2L)
                .build();
    }
}
