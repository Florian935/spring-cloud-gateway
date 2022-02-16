package com.florian935.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
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

            return chain.filter(exchange)

                    // POST FILTER - we can modify the response here before return to client
                    .then(Mono.fromRunnable(() ->
                            exchange
                                    .getResponse()
                                    .getHeaders()
                                    .set("post-filter-header", "post-filter-value")));
        };
    }

    public static class Config {

    }
}
