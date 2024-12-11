package com.fg.fnet.fg.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FgDto {

  private String name;
  private String studentId;
  private String role;
  private String campus;
  private List<String> lcs;

  @Builder
  public FgDto(String name, String studentId, String role, String campus, List<String> lcs) {
    this.name = name;
    this.studentId = studentId;
    this.role = role;
    this.campus = campus;
    this.lcs = lcs;
  }

  public FgLcDto toFgLcDto() {
    return FgLcDto.builder()
        .studentId(studentId)
        .lcs(lcs)
        .build();
  }
}
