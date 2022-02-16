package com.florian935.orderservice.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1.0/orders")
public class OrderController {

    @GetMapping("/message")
    Mono<String> greeting(@RequestHeader("pre-global-filter-header") String preGlobalFilter, @RequestHeader("pre-filter-header") String preFilter) {
        System.out.println(preGlobalFilter);
        System.out.println(preFilter);
        return Mono.just("Hello from order-service");
    }
}
