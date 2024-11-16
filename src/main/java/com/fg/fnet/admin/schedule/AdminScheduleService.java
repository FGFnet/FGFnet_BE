package com.fg.fnet.admin.schedule;

import com.fg.fnet.schedule.entity.Schedule;
import com.fg.fnet.schedule.entity.ScheduleDTO;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class AdminScheduleService {

  private final AdminScheduleRepository adminScheduleRepository;

  public AdminScheduleService(AdminScheduleRepository adminScheduleRepository) {
    this.adminScheduleRepository = adminScheduleRepository;
  }

  public AdminScheduleDto getSchedule() {
    List<Schedule> schedules = adminScheduleRepository.findAll();

    return AdminScheduleDto.builder()
        .schedules(schedules.stream()
            .map(Schedule::toDTO)
            .toList())
        .build();
  }

  public AdminScheduleDto uploadSchedule(AdminScheduleDto request) {

    // 중복 값 검사
    List<ScheduleDTO> scheduleDTOs = request.getSchedules();
    List<Integer> days = scheduleDTOs.stream()
        .map(dto -> {
          if (dto.getDay() == null) {
            throw new IllegalArgumentException("파라미터를 다시 확인해주세요.");
          }
          return dto.getDay();
        })
        .toList();
    List<LocalDate> dates = scheduleDTOs.stream()
        .map(dto -> {
          if (dto.getDate() == null) {
            throw new IllegalArgumentException("파라미터를 다시 확인해주세요.");
          }
          return LocalDate.parse(dto.getDate());
        })
        .toList();

    if (days.size() != days.stream().distinct().count() || dates.size() != dates.stream().distinct()
        .count()) {
      throw new DataIntegrityViolationException("파라미터에 중복된 값이 있습니다. 다시 확인해주세요.");
    }

    List<Schedule> schedules;
    try {
      schedules = adminScheduleRepository.saveAll(request.getSchedules().stream()
          .map(dto -> Schedule.builder()
              .day(dto.getDay())
              .date(LocalDate.parse(dto.getDate()))
              .build())
          .toList());
    } catch (DateTimeParseException e) {
      throw new DateTimeParseException("날짜 형식이 잘못되었습니다. 다시 확인해주세요.", e.getParsedString(),
          e.getErrorIndex());
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("day 또는 date가 중복되었습니다. 다시 확인해주세요.");
    } catch (Exception e) {
      throw new RuntimeException("일정을 저장하는 중에 문제가 발생했습니다.");
    }

    return AdminScheduleDto.builder()
        .schedules(schedules.stream()
            .map(Schedule::toDTO)
            .toList())
        .build();
  }
}
