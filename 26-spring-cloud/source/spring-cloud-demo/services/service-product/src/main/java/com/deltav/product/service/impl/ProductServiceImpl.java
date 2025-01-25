package com.deltav.product.service.impl;

import com.deltav.product.bean.Product;
import com.deltav.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProduct(Long productId) {
        return Product.builder()
                .id(productId)
                .productName("Dummy Product-" + productId)
                .price(BigDecimal.valueOf(99.99))
                .num(2L)
                .build();
    }
}
