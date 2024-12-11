package com.fg.fnet.fg.types;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
  SUPER_ADMIN_FG("최고관리자", "SUPER_ADMIN"),
  ADMIN_FG("관리자FG", "ADMIN"),
  USER_FG("담당FG", "USER"),
  REGISTER_FG("접수FG", "REGISTER");

  private final String description;
  private final String role;

  public static RoleType findByDescription(String description) {
    return Arrays.stream(RoleType.values())
        .filter(v -> v.getDescription().equals(description))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("AuthType에 해당하는 description이 없습니다."));
  }

  public static RoleType findByRole(String role) {
    return Arrays.stream(RoleType.values())
        .filter(v -> v.getRole().equals(role))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("AuthType에 해당하는 role이 없습니다."));
  }
}
