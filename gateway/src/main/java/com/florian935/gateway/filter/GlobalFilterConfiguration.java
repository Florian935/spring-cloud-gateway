package com.florian935.gateway.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GlobalFilterConfiguration {

    @Bean
    public GlobalFilter globalFilter() {

        return (exchange, chain) -> {
            // PRE FILTER

            final ServerHttpRequest request = exchange
                    .getRequest()
                    .mutate()
                    .header("pre-global-filter-header", "pre-global-filter-value")
                    .build();

            final ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();

            return chain.filter(mutatedExchange)
                    // POST FILTER
                    .then(Mono.fromRunnable(() ->
                                    exchange
                                            .getResponse()
                                            .getHeaders()
                                            .add(
                                                    "post-global-filter-header",
                                                    "post-global-filter-value")));
        };
    }
}
