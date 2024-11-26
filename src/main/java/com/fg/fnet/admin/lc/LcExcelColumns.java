package com.fg.fnet.admin.lc;

import com.fg.fnet.common.excel.ExcelColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LcExcelColumns {

  @ExcelColumn(header = "순서")
  private String index;

  @ExcelColumn(header = "LC명")
  private String lcName;

  @ExcelColumn(header = "팀빌딩날짜")
  private String buildingDay;

}

