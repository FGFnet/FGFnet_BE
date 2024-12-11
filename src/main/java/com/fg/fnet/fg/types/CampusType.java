package com.fg.fnet.fg.types;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CampusType {
  HS_CAMPUS("인사캠", "HS"),
  NC_CAMPUS("자과캠", "NC");

  private final String description;
  private final String campus;

  public static CampusType findByDescription(String description) {
    return Arrays.stream(CampusType.values())
        .filter(v -> v.getDescription().equals(description))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("CampusType에 해당하는 description이 없습니다."));
  }

  public static CampusType findByCampus(String campus) {
    return Arrays.stream(CampusType.values())
        .filter(v -> v.getCampus().equals(campus))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("CampusType에 해당하는 campus이 없습니다."));
  }
}
