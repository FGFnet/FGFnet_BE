package com.fg.fnet.fg.types;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
  SUPER_ADMIN_FG("최고관리자", "ROLE_SUPER_ADMIN", "SUPER_ADMIN"),
  ADMIN_FG("관리자FG", "ROLE_ADMIN", "ADMIN"),
  USER_FG("담당FG", "ROLE_USER", "USER"),
  REGISTER_FG("접수FG", "ROLE_REGISTER", "REGISTER");

  private final String description;
  private final String key;
  private final String subkey;

  public static RoleType findByDescription(String description) {
    return Arrays.stream(RoleType.values())
        .filter(v -> v.getDescription().equals(description))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("AuthType에 해당하는 description이 없습니다."));
  }

  public static RoleType findByKey(String key) {
    return Arrays.stream(RoleType.values())
        .filter(v -> v.getKey().equals(key))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("AuthType에 해당하는 key가 없습니다."));
  }

}
