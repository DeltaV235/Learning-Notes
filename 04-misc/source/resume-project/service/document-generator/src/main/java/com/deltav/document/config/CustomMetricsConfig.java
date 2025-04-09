package com.deltav.document.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CustomMetricsConfig {

    private final MeterRegistry registry;

    @Bean
    public Counter requestCounter() {
        return Counter.builder("app_requests_total")
                .description("应用请求总数")
                .tags("type", "all")
                .register(registry);
    }

    @Bean
    public Counter errorCounter() {
        return Counter.builder("app_errors_total")
                .description("应用错误总数")
                .tags("type", "all")
                .register(registry);
    }
} 