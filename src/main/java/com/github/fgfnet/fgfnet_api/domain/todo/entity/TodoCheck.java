package com.github.fgfnet.fgfnet_api.domain.todo.entity;

import com.github.fgfnet.fgfnet_api.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TodoCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_CHECK_ID")
    private Long id;

    private boolean checked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member checker;

    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo;


    @Builder
    public TodoCheck(Member checker, Todo todo) {
        this.checked = false;
        this.checker = checker;
        this.todo = todo;
    }
}
