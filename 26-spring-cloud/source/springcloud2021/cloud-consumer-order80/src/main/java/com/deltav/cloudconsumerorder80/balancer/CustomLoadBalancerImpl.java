package com.deltav.cloudconsumerorder80.balancer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author DeltaV235
 * @version 1.0
 */
@Component
@Slf4j
public class CustomLoadBalancerImpl implements CustomLoadBalancer {
    private final AtomicInteger callCounter = new AtomicInteger(0);

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList) {
        Objects.requireNonNull(serviceInstanceList);

        int nextIndex = callCounter.incrementAndGet() % serviceInstanceList.size();
        log.info("call service instance's index: {}", nextIndex);
        log.info("call counter: {}", callCounter);
        return serviceInstanceList.get(nextIndex);
    }
}
