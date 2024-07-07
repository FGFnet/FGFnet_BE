package com.github.fgfnet.fgfnet_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base-path}")
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }
}
