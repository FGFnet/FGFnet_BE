package com.fg.fnet.fg.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum RoleType {
    ADMIN_FG("관리자", "ADMIN"),
    USER_FG("사용자", "FG"),
    REGISTER_FG("접수용", "REGISTER");

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
