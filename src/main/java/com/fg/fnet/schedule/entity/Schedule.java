package com.fg.fnet.schedule.entity;

import com.fg.fnet.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

  @Column(name = "schedule_day", nullable = false, unique = true)
  private int day;

  @Column(nullable = false, unique = true)
  private LocalDate date;

  @Builder
  public Schedule(int day, LocalDate date) {
    this.day = day;
    this.date = date;
  }

  public ScheduleDTO toDTO() {
    return ScheduleDTO.builder()
        .day(day)
        .date(date.toString())
        .build();
  }

}
