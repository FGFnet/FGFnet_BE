package com.fg.fnet.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponseEntity {

  private final int code;
  private final String message;

  public ErrorResponseEntity(HttpStatus status, String message) {
    this.code = status.value();
    this.message = message;
  }
}
