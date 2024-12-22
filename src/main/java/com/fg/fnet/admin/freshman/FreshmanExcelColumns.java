package com.fg.fnet.admin.freshman;

import com.fg.fnet.common.excel.ExcelColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FreshmanExcelColumns {

  @ExcelColumn(header = "순서")
  private String index;

  @ExcelColumn(header = "이름")
  private String name;

  @ExcelColumn(header = "학번")
  private String studentId;

  @ExcelColumn(header = "전화번호")
  private String phoneNumber;

  @ExcelColumn(header = "계열")
  private String department;

  @ExcelColumn(header = "LC")
  private String lc;
}

