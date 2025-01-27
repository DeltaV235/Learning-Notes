package com.deltav.order.service.impl;

import com.deltav.order.bean.Order;
import com.deltav.order.feign.OrderFeignClient;
import com.deltav.order.service.OrderService;
import com.deltav.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;
    private final LoadBalancerClient loadBalancerClient;
    private final OrderFeignClient orderFeignClient;

    @Autowired
    public OrderServiceImpl(RestTemplate restTemplate,
                            DiscoveryClient discoveryClient,
                            LoadBalancerClient loadBalancerClient,
                            OrderFeignClient orderFeignClient) {
        this.restTemplate = restTemplate;
        this.discoveryClient = discoveryClient;
        this.loadBalancerClient = loadBalancerClient;
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public Order create(Long productId, Long userId) {
//        Product product = getProductWithLoadBalancerAnnotation(productId);
        Product product = orderFeignClient.getProduct(productId);
        BigDecimal totalAmount = product.getPrice().multiply(BigDecimal.valueOf(product.getNum()));
        return Order.builder()
                .id(1L) // Dummy ID
                .totalAmount(totalAmount) // Get from remote and calculate
                .userId(userId)
                .nickName("Dummy NickName") // Dummy nickname
                .address("Dummy Address") // Dummy address
                .productList(Collections.singletonList(product)) // Get from remote
                .build();
    }

    private Product getProductWithLoadBalancerAnnotation(Long productId) {
        String url = "http://service-product/product/" + productId;
        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProductWithLoadBalancer(Long productId) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("service-product");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;
        log.info("Fetching product from URL: {}", url);

        return restTemplate.getForObject(url, Product.class);
    }

    private Product getProduct(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance serviceInstance = instances.get(0);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/product/" + productId;
        log.info("Fetching product from URL: {}", url);

        return restTemplate.getForObject(url, Product.class);
    }
}

