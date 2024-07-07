package com.github.fgfnet.fgfnet_api.domain.todo.entity;

import com.github.fgfnet.fgfnet_api.domain.member.entity.Member;
import com.github.fgfnet.fgfnet_api.domain.todo.entity.enums.TodoTargetType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TODO_ID")
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TodoTargetType target;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member writer;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.ALL)
    private List<TodoCheck> checkers;

    @Builder
    public Todo(String content, Member writer, TodoTargetType target) {
        this.content = content;
        this.writer = writer;
        this.target = target;
    }
}
