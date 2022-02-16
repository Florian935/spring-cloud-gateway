package com.florian935.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {


    @Override
    public GatewayFilter apply(Config config) {

        return (exchange, chain) -> {

            // PRE FILTER - we can perform some operation on the request here

            // If header contain "bad-key", we reject the request and send back
            // response with 401 http status.
            if (exchange.getRequest().getHeaders().containsKey("bad-key")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);


                return exchange.getResponse().setComplete();
            }

            final ServerHttpRequest request = exchange
                    .getRequest()
                    .mutate()
                    .header("pre-filter-header", "pre-filter-value")
                    .build();

            final ServerWebExchange mutatedExchange = exchange.mutate().request(request).build();

            return chain.filter(mutatedExchange)

                    // POST FILTER - we can modify the response here before return to client
                    .then(Mono.fromRunnable(() ->
                            exchange
                                    .getResponse()
                                    .getHeaders()
                                    .add(config.headerKey, config.headerValue)));
        };
    }

    public static class Config {

        private String headerKey;
        private String headerValue;

        public Config() {}

        public Config(String headerKey, String headerValue) {

            this.headerKey = headerKey;
            this.headerValue = headerValue;
        }
    }
}
