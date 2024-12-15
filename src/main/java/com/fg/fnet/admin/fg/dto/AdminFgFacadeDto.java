package com.fg.fnet.admin.fg.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminFgFacadeDto {

  private Integer code;
  private String fg;
  private String fglc;

  @Builder
  public AdminFgFacadeDto(Integer code, String fg, String fglc) {
    this.code = code;
    this.fg = fg;
    this.fglc = fglc;
  }
}
