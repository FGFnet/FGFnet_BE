package com.fg.fnet.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

  @GetMapping("${api.prefix}/auth/session-check")
  public ResponseEntity<String> sessionCheck() {
    try {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String username = authentication.getName();
      return ResponseEntity.ok("session found: " + username);
    } catch (Exception e) {
      return ResponseEntity.status(401).body("session not found");
    }

  }
}
