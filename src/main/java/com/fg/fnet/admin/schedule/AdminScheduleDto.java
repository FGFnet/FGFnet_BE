package com.fg.fnet.admin.schedule;

import com.fg.fnet.schedule.entity.ScheduleDTO;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminScheduleDto {

  private List<ScheduleDTO> schedules;

  @Builder
  public AdminScheduleDto(List<ScheduleDTO> schedules) {
    this.schedules = schedules;
  }
}
