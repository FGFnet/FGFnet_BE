package com.fg.fnet.admin.schedule;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/admin/schedule")
public class AdminScheduleController {

  private final AdminScheduleService adminScheduleService;

  public AdminScheduleController(AdminScheduleService adminScheduleService) {
    this.adminScheduleService = adminScheduleService;
  }

  @GetMapping
  public ResponseEntity<AdminScheduleDto> getSchedule() {
    return ResponseEntity.ok(adminScheduleService.getSchedule());
  }

  @PostMapping
  public ResponseEntity<AdminScheduleDto> createSchedule(
      @Valid @RequestBody AdminScheduleDto request) {
    AdminScheduleDto response = adminScheduleService.uploadSchedule(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
}
