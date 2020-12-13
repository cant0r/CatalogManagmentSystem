package com.cant0r.cms.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String greetClient() {
        return "Hello, World! - said the server\n";
    }
}
