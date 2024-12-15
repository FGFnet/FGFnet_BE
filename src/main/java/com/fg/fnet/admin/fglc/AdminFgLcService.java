package com.fg.fnet.admin.fglc;

import com.fg.fnet.admin.fg.AdminFgRepository;
import com.fg.fnet.admin.lc.AdminLcRepository;
import com.fg.fnet.fg.dto.FgLcDto;
import com.fg.fnet.fg.entity.FgLc;
import com.fg.fnet.lc.Lc;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminFgLcService {

  private final AdminFgLcRepository adminFgLcRepository;
  private final AdminFgRepository adminFgRepository;
  private final AdminLcRepository adminLcRepository;

  public AdminFgLcService(AdminFgLcRepository adminFgLcRepository,
      AdminFgRepository adminFgRepository,
      AdminLcRepository adminLcRepository) {
    this.adminFgLcRepository = adminFgLcRepository;
    this.adminFgRepository = adminFgRepository;
    this.adminLcRepository = adminLcRepository;
  }

  public List<String> mapFgLc(List<FgLcDto> fgLcDtos) {
    List<FgLc> fgLcs = new ArrayList<>();
    List<String> errorFgLcs = new ArrayList<>();
    for (FgLcDto fgLcDto : fgLcDtos) {
      for (String lcName : fgLcDto.getLcs()) {
        Lc lc = adminLcRepository.findByName(lcName);
        if (lc == null) {
          errorFgLcs.add(
              String.format("'%s' 학생의 LC '%s'가 존재하지 않습니다.", fgLcDto.getStudentId(), lcName));
        } else {
          FgLc fgLc = FgLc.builder()
              .fg(adminFgRepository.getByStudentId(fgLcDto.getStudentId()))
              .lc(adminLcRepository.findByName(lcName))
              .build();
          fgLcs.add(fgLc);
        }
      }
    }

    adminFgLcRepository.saveAll(fgLcs);
    return errorFgLcs;
  }
}
