package com.ojt.first_be.util.excel.old;

import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.domain.UploadableFileForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class ExcelHandler {

    private final static String[] EXCEL_EXTENSIONS = {"xls", "xlsx"};

    public static byte[] create(List<StandardProduct> items, UploadableFileForm fileForm) throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(fileForm.name());

        // header 생성
        Row headerRow = sheet.createRow(0);
        int cellIdx = 0;
        for (String header : fileForm.getHeaders()) {
            headerRow.createCell(cellIdx, CellType.STRING).setCellValue(header);
            cellIdx++;
        }

        // body 생성

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        wb.write(out);
        wb.close();

        return out.toByteArray();
    }
}
