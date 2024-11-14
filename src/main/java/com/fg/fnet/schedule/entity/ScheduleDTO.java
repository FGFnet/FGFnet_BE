package com.fg.fnet.schedule.entity;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleDTO {

  private int day;
  private String date;

  @Builder
  public ScheduleDTO(int day, String date) {
    this.day = day;
    this.date = date;
  }

  public Schedule toEntity() {
    return Schedule.builder()
        .day(day)
        .date(LocalDate.parse(date))
        .build();
  }
}
