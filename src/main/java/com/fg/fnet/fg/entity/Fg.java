package com.fg.fnet.fg.entity;

import com.fg.fnet.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Fg extends BaseEntity {

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 12)
    private String studentNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FgType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CampusType campus;

    @Column(nullable = false, length = 100)
    private String password;

}
