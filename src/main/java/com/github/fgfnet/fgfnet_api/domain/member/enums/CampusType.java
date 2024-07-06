package com.github.fgfnet.fgfnet_api.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CampusType {
    // name(value)
    HSC("인문사회과학캠퍼스"),
    NSC("자연과학캠퍼스");

    private final String value;
}
