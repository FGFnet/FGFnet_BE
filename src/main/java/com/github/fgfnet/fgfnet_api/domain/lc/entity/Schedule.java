package com.github.fgfnet.fgfnet_api.domain.lc.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SCHEDULE_ID")
    private Long id;

    @Column(nullable = false)
    private LocalDate buildingDate;

    @Column(nullable = false)
    private Integer buildingDay;

    @Builder
    public Schedule(LocalDate buildingDate, Integer buildingDay) {
        this.buildingDate = buildingDate;
        this.buildingDay = buildingDay;
    }
}
