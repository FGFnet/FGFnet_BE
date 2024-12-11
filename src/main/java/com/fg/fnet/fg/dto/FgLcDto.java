package com.fg.fnet.fg.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FgLcDto {

  private String studentId;
  private List<String> lcs;

  @Builder
  public FgLcDto(String studentId, List<String> lcs) {
    this.studentId = studentId;
    this.lcs = lcs;
  }

}
