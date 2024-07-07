package com.github.fgfnet.fgfnet_api.domain.member.entity;

import com.github.fgfnet.fgfnet_api.domain.lc.entity.MemberLc;
import com.github.fgfnet.fgfnet_api.domain.member.enums.CampusType;
import com.github.fgfnet.fgfnet_api.domain.member.enums.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CampusType campus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MemberLc> lcs;

    @Builder
    public Member(String name, String studentId, CampusType campus, RoleType role) {
        this.name = name;
        this.studentId = studentId;
        this.campus = campus;
        this.role = role;
    }
}
