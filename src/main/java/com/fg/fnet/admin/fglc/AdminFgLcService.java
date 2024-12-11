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

  public Integer mapFgLc(List<FgLcDto> fgLcDtos) {
    List<FgLc> fgLcs = new ArrayList<>();
    for (FgLcDto fgLcDto : fgLcDtos) {
      for (String lcName : fgLcDto.getLcs()) {
        Lc lc = adminLcRepository.findByName(lcName);
        if (lc == null) {
          throw new IllegalArgumentException("LC가 존재하지 않습니다.");
        }

        FgLc fgLc = FgLc.builder()
            .fg(adminFgRepository.getByStudentId(fgLcDto.getStudentId()))
            .lc(adminLcRepository.findByName(lcName))
            .build();
        fgLcs.add(fgLc);
      }
    }

    return adminFgLcRepository.saveAll(fgLcs).size();
  }
}
