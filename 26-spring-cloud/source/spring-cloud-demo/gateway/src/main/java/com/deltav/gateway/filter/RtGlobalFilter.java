package com.deltav.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component
public class RtGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI uri = exchange.getRequest().getURI();
        log.info("Request URL: {} inbound", uri);
        long start = System.currentTimeMillis();
        return chain.filter(exchange)
                .doFinally(signalType -> {
                    long end = System.currentTimeMillis();
                    log.info("Request URL: {} outbound, cost: {} ms", uri, end - start);
                });
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
