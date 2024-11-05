package com.fg.fnet.fg.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CampusType {
    HS_CAMPUS("인사캠"),
    NC_CAMPUS("자과캠");

    private final String description;

    public static CampusType findByDescription(String description) {
        return Arrays.stream(CampusType.values())
                .filter(v -> v.getDescription().equals(description))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("CampusType에 해당하는 description이 없습니다."));
    }
}
