package com.fg.fnet.admin.fg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.fg.fnet.admin.fg.service.AdminFgService;
import com.fg.fnet.fg.dto.FgDto;
import com.fg.fnet.fg.entity.Fg;
import com.fg.fnet.fg.types.CampusType;
import com.fg.fnet.fg.types.RoleType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

@ExtendWith(MockitoExtension.class)
class AdminFgServiceTest {

  @Mock
  private AdminFgRepository adminFgRepository;

  @InjectMocks
  private AdminFgService adminFgService;

  @Test
  void FG_생성() {
    // given
    FgDto fgDto = FgDto.builder()
        .name("홍길동")
        .studentId("20210001")
        .role("접수FG")
        .campus("자과캠")
        .build();

    Fg fg = Fg.builder()
        .name("홍길동")
        .studentId("20210001")
        .role(RoleType.REGISTER_FG)
        .campus(CampusType.NC_CAMPUS)
        .build();

    given(adminFgRepository.save(any(Fg.class))).willReturn(fg);

    // when
    FgDto savedFg = adminFgService.createFg(fgDto);

    // then
    assertEquals("홍길동", savedFg.getName());
    assertEquals("20210001", savedFg.getStudentId());
    assertEquals("REGISTER", savedFg.getRole());
    assertEquals("자과캠", savedFg.getCampus());
    then(adminFgRepository).should().save(any(Fg.class));
  }


  @Test
  void Fgs_생성() {
    // given
    FgDto fgDto1 = FgDto.builder()
        .name("홍길동")
        .studentId("20210001")
        .role("접수FG")
        .campus("인사캠")
        .build();

    FgDto fgDto2 = FgDto.builder()
        .name("김철수")
        .studentId("20210002")
        .role("담당FG")
        .campus("자과캠")
        .build();

    FgDto fgDto3 = FgDto.builder()
        .name("이영희")
        .studentId("20210003")
        .role("담당FG")
        .campus("인사캠")
        .build();

    List<FgDto> fgDtos = List.of(fgDto1, fgDto2, fgDto3);
    given(adminFgRepository.saveAll(any(List.class))).willReturn(List.of());

    // when
    adminFgService.createFgs(fgDtos);

    // then
    then(adminFgRepository).should().saveAll(any(List.class));
  }


  @Test
  void FG_학번_중복_생성() {
    // given
    FgDto fgDto1 = FgDto.builder()
        .name("홍길동")
        .studentId("20210001")
        .role("접수FG")
        .campus("인사캠")
        .build();

    FgDto fgDto2 = FgDto.builder()
        .name("김철수")
        .studentId("20210001")
        .role("담당FG")
        .campus("자과캠")
        .build();

    List<FgDto> fgDtos = List.of(fgDto1, fgDto2);
    given(adminFgRepository.saveAll(any(List.class))).willThrow(
        new DataIntegrityViolationException(""));

    // when & then
    assertThrows(DataIntegrityViolationException.class, () -> adminFgService.createFgs(fgDtos));
  }
}