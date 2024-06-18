package com.ojt.first_be.domain;

import com.ojt.first_be.util.excel.ExcelColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.poi.ss.usermodel.Row;

@ToString
@Getter
@NoArgsConstructor
public class Category implements Uploadable {

    @ExcelColumn(name = "대분류코드")
    private int code;

    @ExcelColumn(name = "대분류명")
    private String name;

    @Builder
    public Category(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public void setValuesFromExcel(Row row) {
        this.code = (int) row.getCell(0).getNumericCellValue();
        this.name = row.getCell(1).getStringCellValue();
    }
}
