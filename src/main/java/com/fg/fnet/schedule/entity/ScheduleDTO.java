package com.fg.fnet.schedule.entity;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleDTO {

  private Integer day;
  private String date;

  @Builder
  public ScheduleDTO(Integer day, String date) {
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
