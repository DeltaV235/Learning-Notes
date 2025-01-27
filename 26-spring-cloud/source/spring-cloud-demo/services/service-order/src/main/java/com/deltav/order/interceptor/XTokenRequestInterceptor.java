package com.deltav.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
//@Component
public class XTokenRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        log.info("X-Token request interceptor...");
        template.header("X-Token", UUID.randomUUID().toString());
    }
}
