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
public class Partner implements Uploadable {

    @ExcelColumn(name = "협력사코드")
    private String code;

    @ExcelColumn(name = "협력사명")
    private String name;

    @Builder
    public Partner(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public void setValuesFromExcel(Row row) {
        this.code = row.getCell(0).getStringCellValue();
        this.name = row.getCell(1).getStringCellValue();
    }
}
