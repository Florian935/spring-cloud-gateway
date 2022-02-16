package com.florian935.productservice.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1.0/products")
public class ProductController {

    @GetMapping("/message")
    Mono<String> greeting() {

        return Mono.just("Hello from product-service");
    }
}
