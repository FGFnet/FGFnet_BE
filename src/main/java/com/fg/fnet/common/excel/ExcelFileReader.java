package com.fg.fnet.common.excel;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

public class ExcelFileReader {

  @Getter
  private List<String> header = new ArrayList<>();

  @Getter
  private List<List<String>> data = new ArrayList<>();

  private int columnNum;

  public ExcelFileReader readFirstSheet(MultipartFile file) {
    ExcelFileReader excelFileHandler = new ExcelFileReader();
    Sheet sheet = getWorkbook(file).getSheetAt(0);
    int rowIdx = 0;
    List<String> rowData;

    setHeader(sheet.getRow(rowIdx++));
    this.columnNum = header.size();

    for (; rowIdx < sheet.getPhysicalNumberOfRows(); rowIdx++) {
      Row row = sheet.getRow(rowIdx);
      rowData = new ArrayList<>();

      for (int colIdx = 0; colIdx < columnNum; colIdx++) {
        Cell cell = row.getCell(colIdx);
        if (cell == null) {
          throw new IllegalArgumentException(
              String.format("%d행 %d열의 셀이 비어있습니다.", rowIdx + 1, colIdx + 1));
        } else {
          rowData.add(getCellValueAsString(cell));
        }
      }

      this.data.add(rowData);
    }

    return excelFileHandler;
  }

  private Workbook getWorkbook(MultipartFile file) {
    try (InputStream is = file.getInputStream()) {
      if (Objects.requireNonNull(file.getOriginalFilename()).endsWith("xls")) {
        return new HSSFWorkbook(is);
      } else if (file.getOriginalFilename().endsWith("xlsx")) {
        return new XSSFWorkbook(is);
      } else {
        throw new IllegalArgumentException("지원하지 않는 파일 형식입니다.");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("엑셀 파일을 읽는 중 오류가 발생했습니다.");
    }
  }

  private void setHeader(Row row) {
    for (int colIdx = 0; colIdx < row.getPhysicalNumberOfCells(); colIdx++) {
      Cell cell = row.getCell(colIdx);
      if (cell == null) {
        throw new IllegalArgumentException((colIdx + 1) + "번째 Column의 헤더가 비어있습니다.");
      }
      this.header.add(getCellValueAsString(cell));
    }
  }

  private String getCellValueAsString(Cell cell) {
    if (cell == null) {
      return "";
    }
    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue();
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getLocalDateTimeCellValue().toLocalDate().toString();
        } else {
          double value = cell.getNumericCellValue();
          if (value == (long) value) {
            return String.format("%d", (long) value);
          } else {
            return String.format("%s", value);
          }
        }
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      case FORMULA:
        return cell.getCellFormula();
      default:
        return "";
    }
  }


  @Override
  public String toString() {
    return "ExcelFileHandler{" +
        "header=" + header +
        ", data=" + data +
        '}';
  }
}