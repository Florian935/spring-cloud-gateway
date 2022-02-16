package com.florian935.orderservice.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/order")
public class OrderController {

    @GetMapping("/message")
    String greeting() {

        return "Hello from order-service";
    }
}
