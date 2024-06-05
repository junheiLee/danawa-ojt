package com.ojt.first_be.util.excel;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Component
public class PostExcelHandler {

    private final static String[] EXCEL_EXTENSIONS = {"xls", "xlsx"};

    /**
     * 해당 파일이 Excel 확장자(.xlsx, .xls)인지 확인
     *
     * @param fileName 확인할 파일 이름 (uploadFile.getOriginalFilename())
     * @return Excel 파일이 맞을 경우 true 반환
     */
    public static boolean canParse(String fileName) {

        String extension = FilenameUtils.getExtension(fileName);
        return Arrays.asList(EXCEL_EXTENSIONS).contains(extension);
    }

    /**
     * 해당 파일의 첫 번째 줄 데이터(Header)를 List로 반환
     *
     * @param inputStream 확인할 Excel 파일의 InputStream (uploadFile.getInputStream())
     * @return 첫 번째 줄 데이터 문자열 리스트
     */
    public static List<String> getHeaders(InputStream inputStream) throws IOException {

        Workbook wb = WorkbookFactory.create(inputStream);

        Iterator<Cell> headersIterator = getHeadersIterator(wb);    // Header 정보인 0번째 sheet의 0번째 row의 Iterator를 가져욘다.
        List<String> headers = toStringList(headersIterator);       // Iterator를 List<String>으로 변환

        wb.close();

        return headers;
    }

    private static Iterator<Cell> getHeadersIterator(Workbook wb) {
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);

        return row.cellIterator();
    }

    private static List<String> toStringList(Iterator<Cell> cells) {
        List<String> headers = new ArrayList<>();

        while (cells.hasNext()) {
            Cell cell = cells.next();
            headers.add(cell.getStringCellValue());
        }
        return headers;
    }

}
