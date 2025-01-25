package com.deltav.product.bean;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {
    private Long id;
    private BigDecimal price;
    private String productName;
    private Long num;
}
