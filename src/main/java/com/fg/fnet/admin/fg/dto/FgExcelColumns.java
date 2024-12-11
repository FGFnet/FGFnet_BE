package com.fg.fnet.admin.fg.dto;

import com.fg.fnet.common.excel.ExcelColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FgExcelColumns {

  @ExcelColumn(header = "순서")
  private String index;

  @ExcelColumn(header = "이름")
  private String name;

  @ExcelColumn(header = "학번")
  private String studentId;

  @ExcelColumn(header = "역할")
  private String role;

  @ExcelColumn(header = "캠퍼스")
  private String campus;

  @ExcelColumn(header = "담당LC1")
  private String lc01;

  @ExcelColumn(header = "담당LC2")
  private String lc02;

  @ExcelColumn(header = "담당LC3")
  private String lc03;
}
