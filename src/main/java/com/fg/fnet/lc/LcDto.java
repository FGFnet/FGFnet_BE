package com.fg.fnet.lc;

import com.fg.fnet.schedule.entity.Schedule;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LcDto {

  private String lcName;
  private String buildingDate;

  @Builder
  public LcDto(String lcName, String buildingDate) {
    this.lcName = lcName;
    this.buildingDate = buildingDate;
  }

  public Lc toEntity() {
    return Lc.builder()
        .name(lcName)
        .schedule(Schedule.builder().date(LocalDate.parse(buildingDate)).build())
        .build();
  }
}