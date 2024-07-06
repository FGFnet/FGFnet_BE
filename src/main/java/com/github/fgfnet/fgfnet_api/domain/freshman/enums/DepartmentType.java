package com.github.fgfnet.fgfnet_api.domain.freshman.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DepartmentType {
    NC("자연과학계열"),
    SC("사회과학계열"),
    HS("인문사회과학계열"),
    EN("공학계열");

    private final String description;
}
