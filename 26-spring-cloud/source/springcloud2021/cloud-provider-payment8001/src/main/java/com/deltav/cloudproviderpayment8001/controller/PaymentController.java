package com.deltav.cloudproviderpayment8001.controller;

import com.deltav.cloudproviderpayment8001.service.PaymentService;
import com.deltav.springcloud.entities.Payment;
import com.deltav.springcloud.vo.CommonResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 */
@RestController
@Slf4j
public class PaymentController {
    private static final String CONTROLLER_DESCRIPTION = "microservice test API for payment module";

    @Resource
    private PaymentService paymentService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/payment/{id}")
    public CommonResultVO<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment;
        try {
            payment = paymentService.getPaymentById(id);
            return CommonResultVO.successWithData(payment, applicationName, serverPort, CONTROLLER_DESCRIPTION);
        } catch (Exception e) {
            log.error("get payment info failed", e);
            return CommonResultVO.failedWithoutData(applicationName, serverPort, CONTROLLER_DESCRIPTION);
        }
    }

    @PostMapping("/payment")
    public CommonResultVO<Long> addPayment(@RequestBody Payment payment) {
        try {
            Long retVal = paymentService.addPayment(payment);
            return CommonResultVO.successWithData(retVal, applicationName, serverPort, CONTROLLER_DESCRIPTION);
        } catch (Exception e) {
            log.error("insert payment info failed", e);
            return CommonResultVO.failedWithoutData(applicationName, serverPort, CONTROLLER_DESCRIPTION);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("service: {}", service);
        }

        for (ServiceInstance instance : discoveryClient.getInstances(services.get(0))) {
            log.info("instance of Payment: {} - {} - {} - {}",
                    instance.getInstanceId(),
                    instance.getHost(),
                    instance.getPort(),
                    instance.getUri());
        }

        return this.discoveryClient;
    }
}
