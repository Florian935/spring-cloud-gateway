package com.florian935.productservice.controller.v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2.0/products")
public class Productv2Controller {

    @GetMapping("/message")
    String greetingv2() {

        return "Hello from product-service [v2]";

    }
}
