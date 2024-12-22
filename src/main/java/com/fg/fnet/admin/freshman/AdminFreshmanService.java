package com.fg.fnet.admin.freshman;

import com.fg.fnet.admin.lc.AdminLcRepository;
import com.fg.fnet.common.excel.ExcelDTOMapper;
import com.fg.fnet.common.excel.ExcelFileReader;
import com.fg.fnet.freshman.DepartType;
import com.fg.fnet.freshman.Freshman;
import com.fg.fnet.freshman.FreshmanDto;
import com.fg.fnet.lc.Lc;
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
public class AdminFreshmanService {

  private final AdminFreshmanRepository adminFreshmanRepository;
  private final AdminLcRepository adminLcRepository;

  public AdminFreshmanService(AdminFreshmanRepository adminFreshmanRepository,
      AdminLcRepository adminLcRepository) {
    this.adminFreshmanRepository = adminFreshmanRepository;
    this.adminLcRepository = adminLcRepository;
  }

  public AdminFreshmanDto.Freshmans getFreshmans() {
    List<Freshman> freshmans = adminFreshmanRepository.findAll();

    return AdminFreshmanDto.Freshmans.builder()
        .freshmans(freshmans.stream()
            .map(Freshman::toDTO)
            .toList())
        .build();
  }

  public AdminFreshmanDto createFreshman() {
    return new AdminFreshmanDto();
  }

  private List<FreshmanDto> getFreshmansFromFile(MultipartFile file) {
    // parse excel file
    List<FreshmanDto> freshmanDtos = new ArrayList<>();
    Set<String> studentIdSet = new HashSet<>();
    ExcelFileReader excelFile = new ExcelFileReader();
    FreshmanExcelColumns freshmanExcelColumns = new FreshmanExcelColumns();

    try {
      excelFile.readFirstSheet(file);
    } catch (IOException e) {
      throw new IllegalArgumentException("파일을 읽는 중 오류가 발생했습니다.");
    }

    Map<String, Integer> columnIndexMap = ExcelDTOMapper.getExcelColumnIndex(excelFile.getHeader(),
        freshmanExcelColumns.getClass());

    for (List<String> row : excelFile.getData()) {
      String name = row.get(columnIndexMap.get("name"));
      String studentId = row.get(columnIndexMap.get("studentId"));
      String phoneNumber = row.get(columnIndexMap.get("phoneNumber"));
      String department = row.get(columnIndexMap.get("department"));
      String lc = row.get(columnIndexMap.get("lc"));

      if (!studentIdSet.add(studentId)) {
        throw new DataIntegrityViolationException(
            String.format("중복된 학번 '%s'이(가) 있습니다. 다시 확인해주세요.", studentId)
        );
      }

      freshmanDtos.add(FreshmanDto.builder()
          .name(name)
          .studentId(studentId)
          .phoneNum(phoneNumber)
          .department(department)
          .lc(lc)
          .build());
    }

    return freshmanDtos;
  }

  public AdminFreshmanDto.Message uploadFreshmans(MultipartFile file) throws IOException {
    List<FreshmanDto> freshmanDtos = getFreshmansFromFile(file);
    List<Freshman> freshmans = new ArrayList<>();
    String createFreshmanMessage = "Freshman 생성이 완료되었습니다.";

    for (FreshmanDto freshmanDto : freshmanDtos) {
      Lc lc = adminLcRepository.findByName(freshmanDto.getLc());
      if (lc == null) {
        createFreshmanMessage = "Freshman 생성 중 오류가 발생했습니다.";
        throw new DataIntegrityViolationException("LC가 존재하지 않습니다.");
      }

      Freshman freshman = Freshman.builder()
          .name(freshmanDto.getName())
          .studentId(freshmanDto.getStudentId())
          .phoneNumber(freshmanDto.getPhoneNum())
          .department(DepartType.findByDescription(freshmanDto.getDepartment()))
          .lc(lc)
          .registered(false)
          .build();
      freshmans.add(freshman);
    }

    try {
      adminFreshmanRepository.saveAll(freshmans);
    } catch (DataIntegrityViolationException e) {
      createFreshmanMessage = "Freshman 생성 중 오류가 발생했습니다.";
      throw new DataIntegrityViolationException("LC가 지정되지 않았거나, 중복된 학번이 존재합니다.");
    } catch (Exception e) {
      createFreshmanMessage = "Freshman 생성 중 오류가 발생했습니다.";
      throw new RuntimeException("Freshman 업로드 중 오류가 발생했습니다.");
    }

    return AdminFreshmanDto.Message.builder()
        .message(createFreshmanMessage)
        .build();
  }
}

