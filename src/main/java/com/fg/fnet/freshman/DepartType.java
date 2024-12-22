package com.fg.fnet.freshman;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DepartType {

  DPT_NC("자연과학계열", "NC"),
  DPT_EN("공학계열", "EN"),
  DPT_HS("인문사회과학계열", "HS"),
  DTP_SC("사회과학계열", "SC");

  private final String description;
  private final String department;

  public static DepartType findByDescription(String description) {
    return Arrays.stream(DepartType.values())
        .filter(v -> v.getDescription().equals(description))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("DepartType에 해당하는 description이 없습니다."));
  }
}
