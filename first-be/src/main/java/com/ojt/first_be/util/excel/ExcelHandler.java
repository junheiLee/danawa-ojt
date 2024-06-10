package com.ojt.first_be.util.excel;

import com.ojt.first_be.domain.Uploadable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Component
public class ExcelHandler {

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
     * @param targetFile 헤더를 가져올 대상 파일
     * @return 첫 번째 줄 데이터 문자열 리스트
     */
    public static List<String> getHeaders(MultipartFile targetFile) throws IOException {

        Workbook wb = WorkbookFactory.create(targetFile.getInputStream());

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

    /**
     * 엑셀 파일을 한 줄씩 읽어 객체로 변경
     *
     * @param inputStream          대상 엑셀 파일의 inputStream
     * @param itemBuilderFromExcel 엑셀 파일이 담고 있는 객체를 Row로 부터 가져오는 함수
     * @param <T>                  엑셀 파일을 읽어 db에 저장할 수 있는 객체
     * @return 객체 리스트
     */
    public <T extends Uploadable> List<T> getObjectList(InputStream inputStream,
                                                        Function<Row, T> itemBuilderFromExcel) throws IOException {

        List<T> items = new ArrayList<>();
        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(0);

        for (Row row : sheet) {

            // 최초 Row는 개별 값이 아닌 헤더값
            if (row.getRowNum() == 0) {
                continue;
            }

            items.add(itemBuilderFromExcel.apply(row));
        }
        return items;
    }

}
