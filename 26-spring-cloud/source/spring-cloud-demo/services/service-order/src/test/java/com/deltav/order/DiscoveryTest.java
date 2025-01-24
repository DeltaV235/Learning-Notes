package com.deltav.order;


import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@SpringBootTest
public class DiscoveryTest {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private NacosDiscoveryClient nacosDiscoveryClient;

    @Test
    void testDiscoveryClient() {
        for (String service : discoveryClient.getServices()) {
            System.out.println("service: " + service);
            for (ServiceInstance instance : discoveryClient.getInstances(service)) {
                System.out.println("instance host: " + instance.getHost() + ", port: " + instance.getPort());
            }
        }
    }

    @Test
    void testNacosDiscoveryClient() {
        for (String service : nacosDiscoveryClient.getServices()) {
            System.out.println("service: " + service);
            for (ServiceInstance instance : nacosDiscoveryClient.getInstances(service)) {
                System.out.println("instance host: " + instance.getHost() + ", port: " + instance.getPort());
            }
        }
    }
}
