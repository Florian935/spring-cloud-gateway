package com.florian935.orderservice.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1.0/orders")
public class OrderController {

    @GetMapping("/message")
    Mono<String> greeting() {

        return Mono.just("Hello from order-service");
    }
}
