package com.deltav.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class AddTokenGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> chain.filter(exchange)
                .then(addResponseHeader(exchange, config));
    }

    private Mono<Void> addResponseHeader(ServerWebExchange exchange, NameValueConfig config) {
        return Mono.fromRunnable(() ->
                exchange.getResponse()
                        .getHeaders()
                        .add(config.getName(), config.getValue())
        );
    }
}
