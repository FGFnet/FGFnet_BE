package com.github.fgfnet.fgfnet_api.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    ADMIN("ROLE_ADMIN"),
    REGISTER("ROLE_REGISTER"),
    FG("ROLE_FG");

    private final String authority;
}
