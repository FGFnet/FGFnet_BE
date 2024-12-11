package com.fg.fnet.admin.fg.service;

import com.fg.fnet.admin.fg.dto.FgExcelColumns;
import com.fg.fnet.admin.fglc.AdminFgLcService;
import com.fg.fnet.common.excel.ExcelDTOMapper;
import com.fg.fnet.common.excel.ExcelFileReader;
import com.fg.fnet.fg.dto.FgDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AdminFgServiceFacade {

  private final AdminFgService adminFgService;
  private final AdminFgLcService adminFgLcService;

  public AdminFgServiceFacade(AdminFgService adminFgService, AdminFgLcService adminFgLcService) {
    this.adminFgService = adminFgService;
    this.adminFgLcService = adminFgLcService;
  }

  private List<FgDto> getFgsFromFile(MultipartFile file) {
    // parse excel file
    List<FgDto> fgDtos = new ArrayList<>();
    Set<String> fgNames = new HashSet<>();
    ExcelFileReader excelFile = new ExcelFileReader();
    FgExcelColumns fgExcelColumns = new FgExcelColumns();

    try {
      excelFile.readFirstSheet(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("파일을 읽는 중 오류가 발생했습니다.");
    }

    Map<String, Integer> columnIndexMap = ExcelDTOMapper.getExcelColumnIndex(excelFile.getHeader(),
        fgExcelColumns.getClass());

    for (List<String> row : excelFile.getData()) {
      String name = row.get(columnIndexMap.get("name"));
      String studentId = row.get(columnIndexMap.get("studentId"));
      String role = row.get(columnIndexMap.get("role"));
      String campus = row.get(columnIndexMap.get("campus"));
      List<String> lcs = List.of(
          row.get(columnIndexMap.get("lc01")),
          row.get(columnIndexMap.get("lc02")),
          row.get(columnIndexMap.get("lc03"))
      );

      if (!fgNames.add(studentId)) {
        throw new DataIntegrityViolationException(
            String.format("중복된 힉번 '%s'이(가) 있습니다. 다시 확인해주세요.", studentId)
        );
      }

      fgDtos.add(FgDto.builder()
          .name(name)
          .studentId(studentId)
          .role(role)
          .campus(campus)
          .lcs(Objects.equals(lcs.get(0), "-") ? List.of() : lcs)
          .build());
    }

    return fgDtos;
  }

  public List<Integer> uploadFgs(MultipartFile file) throws IOException {
    List<FgDto> fgDtos = getFgsFromFile(file);
    Integer createdCount = adminFgService.createFgs(fgDtos);
    Integer mappedCount = adminFgLcService.mapFgLc(fgDtos.stream().map(FgDto::toFgLcDto).toList());

    return List.of(createdCount, mappedCount);
  }

}
