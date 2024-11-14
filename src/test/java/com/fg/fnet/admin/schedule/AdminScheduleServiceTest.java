package com.fg.fnet.admin.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fg.fnet.schedule.entity.ScheduleDTO;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdminScheduleServiceTest {

  @Mock
  private AdminScheduleRepository adminScheduleRepository;

  @InjectMocks
  private AdminScheduleService adminScheduleService;

  @Test
  void Schedule_생성() {
    // given
    ScheduleDTO scheduleDto1 = ScheduleDTO.builder().day(1).date("2021-02-25").build();

    ScheduleDTO scheduleDto2 = ScheduleDTO.builder().day(2).date("2021-02-26").build();

    ScheduleDTO scheduleDto3 = ScheduleDTO.builder().day(3).date("2021-02-27").build();

    // when
    when(adminScheduleRepository.saveAll(anyList())).thenReturn(
        List.of(scheduleDto1.toEntity(), scheduleDto2.toEntity(), scheduleDto3.toEntity()));

    AdminScheduleDto requestDto = AdminScheduleDto.builder()
        .schedules(List.of(scheduleDto1, scheduleDto2, scheduleDto3)).build();

    AdminScheduleDto savedDto = adminScheduleService.uploadSchedule(requestDto);

    // then
    verify(adminScheduleRepository, times(1)).saveAll(anyList());
    assertEquals(3, savedDto.getSchedules().size());
  }

  @Test
  void Schedule_생성시_날짜가_없는_경우_에러() {
    // given
    ScheduleDTO scheduleDto1 = ScheduleDTO.builder().day(1).date("2021-02-31").build();

    // when & then
    AdminScheduleDto requestDto = AdminScheduleDto.builder().schedules(List.of(scheduleDto1))
        .build();

    assertThrows(DateTimeParseException.class,
        () -> adminScheduleService.uploadSchedule(requestDto));
  }


  @Test
  void Schedule_생성시_값이_중복인_경우_에러() {
    // given
    ScheduleDTO scheduleDto1 = ScheduleDTO.builder().day(1).date("2021-02-25").build();

    ScheduleDTO scheduleDto2 = ScheduleDTO.builder().day(1).date("2021-02-26").build();

    AdminScheduleDto requestDto = AdminScheduleDto.builder()
        .schedules(List.of(scheduleDto1, scheduleDto2)).build();

    // when & then
    assertThrows(IllegalArgumentException.class,
        () -> adminScheduleService.uploadSchedule(requestDto));
  }

}