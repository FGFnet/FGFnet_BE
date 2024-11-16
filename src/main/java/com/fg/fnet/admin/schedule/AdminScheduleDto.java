package com.fg.fnet.admin.schedule;

import com.fg.fnet.schedule.entity.ScheduleDTO;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminScheduleDto {

  @NotNull(message = "Request Body를 확인해주세요.")
  private List<ScheduleDTO> schedules;

  @Builder
  public AdminScheduleDto(List<ScheduleDTO> schedules) {
    this.schedules = schedules;
  }
}
