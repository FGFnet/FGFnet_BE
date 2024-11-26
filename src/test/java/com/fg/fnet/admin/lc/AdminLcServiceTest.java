package com.fg.fnet.admin.lc;

import com.fg.fnet.admin.schedule.AdminScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdminLcServiceTest {

  @Mock
  private AdminLcRepository adminLcRepository;

  @Mock
  private AdminScheduleRepository adminScheduleRepository;

  @InjectMocks
  private AdminLcService adminLcService;

  @Test
  void LC_생성() {
    // given

    // when

    // then
  }

  @Test
  void LC_생성시_스케줄이_없는_경우_에러() {
    // given

    // when

    // then
  }

  @Test
  void LC_생성시_동일한_lcName이_존재하는_경우_에러() {
    // given

    // when

    // then
  }
}