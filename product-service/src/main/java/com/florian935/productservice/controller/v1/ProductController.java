package com.florian935.productservice.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1.0/product")
public class ProductController {

    @GetMapping("/message")
    String greeting() {

        return "Hello from product-service";
    }
}
