package com.fg.fnet.admin.lc;

import com.fg.fnet.admin.schedule.AdminScheduleRepository;
import com.fg.fnet.common.excel.ExcelDTOMapper;
import com.fg.fnet.common.excel.ExcelFileReader;
import com.fg.fnet.lc.Lc;
import com.fg.fnet.schedule.entity.Schedule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminLcService {

  private final AdminLcRepository adminLcRepository;
  private final AdminScheduleRepository adminScheduleRepository;

  public AdminLcService(AdminLcRepository adminLcRepository,
      AdminScheduleRepository adminScheduleRepository) {
    this.adminLcRepository = adminLcRepository;
    this.adminScheduleRepository = adminScheduleRepository;
  }

  public AdminLcDto getLcs() {
    List<Lc> lcs = adminLcRepository.findAll();

    return AdminLcDto.builder()
        .lcs(lcs.stream()
            .map(Lc::toDTO)
            .toList())
        .build();
  }

  public AdminLcDto uploadLcs(MultipartFile file) throws IOException {
    ExcelFileReader excelFile = new ExcelFileReader();
    excelFile.readFirstSheet(file);

    LcExcelColumns lcExcelColumns = new LcExcelColumns();
    Map<String, Integer> columnIndexMap = ExcelDTOMapper.getExcelColumnIndex(excelFile.getHeader(),
        lcExcelColumns.getClass());

    List<Lc> lcs = new ArrayList<>();
    Set<String> lcNames = new HashSet<>();

    for (List<String> row : excelFile.getData()) {
      String lcName = row.get(columnIndexMap.get("lcName"));

      if (!lcNames.add(lcName)) {
        throw new DataIntegrityViolationException(
            String.format("중복된 LC 이름 '%s'이(가) 있습니다. 다시 확인해주세요.", lcName)
        );
      }

      lcNames.add(row.get(columnIndexMap.get("lcName")));

      Integer scheduleDay = Integer.valueOf(row.get(columnIndexMap.get("buildingDay")));
      Schedule schedule = adminScheduleRepository.getReferenceByDay(scheduleDay);

      Lc lc = Lc.builder()
          .name(lcName)
          .schedule(schedule)
          .build();
      lcs.add(lc);
    }

    try {
      adminLcRepository.saveAll(lcs);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityViolationException("Schedule이 지정되지 않았거나, 중복된 LC존재합니다.");
    } catch (Exception e) {
      throw new RuntimeException("LC 업로드 중 오류가 발생했습니다.");
    }

    return AdminLcDto.builder()
        .lcs(lcs.stream()
            .map(Lc::toDTO)
            .toList())
        .build();
  }
}

