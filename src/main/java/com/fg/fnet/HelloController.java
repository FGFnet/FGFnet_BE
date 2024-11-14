package com.fg.fnet;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}")
public class HelloController {

  @GetMapping("/hello")
  public String getHello() {
    return "hello";
  }
}
