package com.deltav.cloudconsumerorder80.balancer;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 */
public interface CustomLoadBalancer {
    /**
     * use round-robin method to get next service instance
     *
     * @param serviceInstanceList all service instances info
     * @return next calling service instance info
     */
    ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList);
}
