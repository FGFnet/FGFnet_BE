package com.github.fgfnet.fgfnet_api.domain.lc.entity;

import com.github.fgfnet.fgfnet_api.domain.freshman.entity.Freshman;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LC_ID")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer freshmanCount;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Integer registeredCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCHEDULE_ID")
    private Schedule buildingDate;

    @OneToMany(mappedBy = "lc", fetch = FetchType.LAZY)
    private List<MemberLc> fgs;

    @OneToMany(mappedBy = "lc", fetch = FetchType.LAZY)
    private List<Freshman> freshmans;
}
