package com.fg.fnet.common.excel;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelDTOMapper {

  public static <T> Map<String, Integer> getExcelColumnIndex(List<String> excelHeaders,
      Class<T> dtoClass) {
    Map<String, Integer> columnIndexMap = new HashMap<>();
    Field[] fields = dtoClass.getDeclaredFields();

    // header 개수가 다르면 예외 발생
    if (excelHeaders.size() != fields.length) {
      throw new IllegalArgumentException("엑셀 파일의 헤더를 확인해주세요");
    }

    // header를 찾지 못하면 예외 발생
    for (String header : excelHeaders) {
      boolean isExist = false;
      for (Field field : fields) {
        ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
        if (excelColumn != null && excelColumn.header().equals(header)) {
          isExist = true;
          columnIndexMap.put(field.getName(), columnIndexMap.size());
          break;
        }
      }
      if (!isExist) {
        throw new IllegalArgumentException("엑셀 파일의 헤더를 확인해주세요");
      }
    }

    return columnIndexMap;
  }
}

