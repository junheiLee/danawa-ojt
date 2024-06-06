package com.ojt.first_be.excel;

import com.ojt.first_be.domain.Category;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryExcelHandler implements ExcelHandler {

    @Override
    public List<Category> toObjectList(InputStream inputStream) throws IOException {

        List<Category> categories = new ArrayList<>();
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {

            // 최초 Row는 값이 아닌 헤더값
            if (row.getRowNum() == 0) {
                continue;
            }

            categories.add(
                    Category.builder()
                            .code((int) row.getCell(0).getNumericCellValue())
                            .name(row.getCell(1).getStringCellValue().trim())
                            .build()
            );
        }
        return categories;
    }
}
