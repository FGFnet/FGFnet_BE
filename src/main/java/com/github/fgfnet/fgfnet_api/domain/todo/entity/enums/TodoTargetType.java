package com.github.fgfnet.fgfnet_api.domain.todo.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TodoTargetType {
    ME("개인"),
    LC("담당FG"),
    ALL("전체");

    private final String description;
}
