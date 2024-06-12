package com.ojt.first_be.util.excel;

import com.ojt.first_be.domain.Uploadable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ExcelConverter {

    private final static String[] EXCEL_EXTENSIONS = {"xls", "xlsx"};
    private final static String PARSE_ROW_METHOD_NAME = "setValuesFormExcel";

    /**
     * 해당 파일이 Excel 확장자(.xlsx, .xls)인지 확인
     *
     * @param fileName 대상 파일 풀네임 (multipartFile.getOriginalFilename())
     * @return 처리할 수 있는 엑셀 파일이면 True 반환
     */
    public boolean supports(String fileName) {

        String extension = FilenameUtils.getExtension(fileName);
        return Arrays.asList(EXCEL_EXTENSIONS).contains(extension);
    }

    /**
     * 엑셀 파일을 한 줄씩 읽어 객체로 변경
     *
     * @param inputStream  대상 엑셀 파일의 inputStream
     * @param targetDomain 객체 타입
     * @param <T>          엑셀 파일을 읽어 db에 저장할 수 있는 객체
     * @return 객체 리스트
     */
    public <T extends Uploadable> List<T> parse(InputStream inputStream,
                                                Class<T> targetDomain) throws IOException {

        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(0); // sheet 한 개

        validHeaders(sheet.getRow(0), getFields(targetDomain)); // 첫 행의 정보로 타겟 도메인에 저장할 수 있는 헤더인지 확인
        List<T> items = parseBody(sheet, targetDomain);

        wb.close();
        return items;
    }

    private void validHeaders(Row row, List<String> fieldNames) {

        List<String> excelHeaders = new ArrayList<>();
        row.cellIterator()
                .forEachRemaining(
                        eachCell -> excelHeaders.add(eachCell.getStringCellValue())
                );

        if (!excelHeaders.equals(fieldNames)) {
            throw new RuntimeException("임시" + "저장 못하지롱");
        }
    }

    private <T> List<String> getFields(Class<T> domain) {

        List<String> clazzFields = new ArrayList<>();

        Arrays.stream(domain.getDeclaredFields())
                .filter(e -> e.isAnnotationPresent(ExcelColumn.class))
                .forEach(e -> clazzFields.add(e.getAnnotation(ExcelColumn.class).name()));

        return clazzFields;
    }

    private <T extends Uploadable> List<T> parseBody(Sheet sheet, Class<T> targetDomain) {

        List<T> items = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == 0) { // 최초 Row는 개별 값이 아닌 헤더값
                continue;
            }
            items.add(getItemFormRow(row, targetDomain));
        }
        return items;
    }

    private <T extends Uploadable> T getItemFormRow(Row row, Class<T> domain) {

        try {
            T item = domain.getDeclaredConstructor().newInstance();
            domain.getMethod(PARSE_ROW_METHOD_NAME, Row.class).invoke(item, row);
            return item;

        } catch (NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("임시");

        }
    }

}
