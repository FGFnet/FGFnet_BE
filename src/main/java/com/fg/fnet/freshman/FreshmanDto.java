package com.fg.fnet.freshman;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FreshmanDto {

  private String name;
  private String studentId;
  private String phoneNum;
  private String department;
  private String lc;

  @Builder
  public FreshmanDto(String name, String studentId, String phoneNum, String department, String lc) {
    this.name = name;
    this.studentId = studentId;
    this.phoneNum = phoneNum;
    this.department = department;
    this.lc = lc;
  }

  public Freshman toEntity() {
    return Freshman.builder()
        .name(name)
        .studentId(studentId)
        .phoneNumber(phoneNum)
        .department(DepartType.findByDescription(department))
        .lc(null)
        .build();
  }
}
