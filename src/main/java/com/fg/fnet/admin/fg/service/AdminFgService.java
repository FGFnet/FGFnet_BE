package com.fg.fnet.admin.fg.service;

import com.fg.fnet.admin.fg.AdminFgRepository;
import com.fg.fnet.admin.fg.dto.AdminFgDto;
import com.fg.fnet.admin.lc.AdminLcRepository;
import com.fg.fnet.fg.dto.FgDto;
import com.fg.fnet.fg.entity.Fg;
import com.fg.fnet.fg.types.CampusType;
import com.fg.fnet.fg.types.RoleType;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminFgService {

  private final AdminFgRepository adminFgRepository;
  private final AdminLcRepository adminLcRepository;

  @Value("${password.init}")
  private String initPassword;

  public AdminFgService(AdminFgRepository adminFgRepository,
      AdminLcRepository adminLcRepository) {
    this.adminFgRepository = adminFgRepository;
    this.adminLcRepository = adminLcRepository;
  }

  // 모든 FG 조회
  public AdminFgDto getFgs() {
    List<Fg> fgs = adminFgRepository.findAll();

    return AdminFgDto.builder()
        .fgs(fgs.stream()
            .map(Fg::toDTO)
            .toList())
        .build();
  }

  // FG 생성
  public FgDto createFg(FgDto fgDto) {
    Fg fg = Fg.builder()
        .name(fgDto.getName())
        .studentId(fgDto.getStudentId())
        .role(RoleType.findByDescription(fgDto.getRole()))
        .campus(CampusType.findByDescription(fgDto.getCampus()))
        .build();

    return adminFgRepository.save(fg).toDTO();
  }

  // FGs 생성
  public void createFgs(List<FgDto> fgDtos) {
    List<Fg> fgs = fgDtos.stream()
        .map(fgDto -> Fg.builder()
            .name(fgDto.getName())
            .studentId(fgDto.getStudentId())
            .role(RoleType.findByDescription(fgDto.getRole()))
            .campus(CampusType.findByDescription(fgDto.getCampus()))
            .password(initPassword)
            .build())
        .toList();

    adminFgRepository.saveAll(fgs);
  }
}
