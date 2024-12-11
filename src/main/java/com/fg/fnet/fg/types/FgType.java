package com.fg.fnet.fg.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FgType {
  ACT("활동기수"),
  MAN("운영기수");

  private final String description;

  public static FgType findByDescription(String description) {
    return java.util.Arrays.stream(FgType.values())
        .filter(v -> v.getDescription().equals(description))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("FgType에 해당하는 description이 없습니다."));
  }
}
