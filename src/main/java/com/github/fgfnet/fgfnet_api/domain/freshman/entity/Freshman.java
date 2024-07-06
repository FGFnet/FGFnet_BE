package com.github.fgfnet.fgfnet_api.domain.freshman.entity;

import com.github.fgfnet.fgfnet_api.domain.freshman.enums.DepartmentType;
import com.github.fgfnet.fgfnet_api.domain.lc.entity.Lc;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Freshman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRESHMAN_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String studentId;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private DepartmentType department;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean registered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LC_ID")
    private Lc lc;

    @Builder
    public Freshman(String name, String studentId, String phoneNumber, DepartmentType department, Lc lc) {
        this.name = name;
        this.studentId = studentId;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.lc = lc;
    }
}
