package com.ojt.first_be.util.excel;

import com.ojt.first_be.domain.Category;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
public class CategoryExcelHandler extends ExcelHandler {

    public Category buildFormExcel(Row row) {

        return Category.builder()
                .code((int) row.getCell(0).getNumericCellValue())
                .name(row.getCell(1).getStringCellValue().trim())
                .build();
    }
}
