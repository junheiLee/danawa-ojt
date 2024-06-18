package com.ojt.first_be.util.excel;

import com.ojt.first_be.domain.Uploadable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class ExcelConverter {

    private final static String[] EXCEL_EXTENSIONS = {"xls", "xlsx"};
    private final static String UPLOADABLE_METHOD_NAME = "setValuesFromExcel";
    private final static int START_INDEX = 0;

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
    public <T extends Uploadable> List<T> parseExcel(InputStream inputStream,
                                                     Class<T> targetDomain) throws IOException {

        Workbook wb = WorkbookFactory.create(inputStream);
        Sheet sheet = wb.getSheetAt(START_INDEX); // sheet 한 개

        validHeaders(sheet.getRow(START_INDEX), getFields(targetDomain)); // 첫 행의 정보로 타겟 도메인에 저장할 수 있는 헤더인지 확인
        List<T> items = parseBody(sheet, targetDomain);

        wb.close();
        return items;
    }

    private void validHeaders(Row headerRow, List<String> fieldNames) {

        List<String> excelHeaders = new ArrayList<>();
        headerRow.cellIterator()
                .forEachRemaining(
                        eachCell -> excelHeaders.add(eachCell.getStringCellValue())
                );

        if (!excelHeaders.equals(fieldNames)) {
            throw new RuntimeException("임시" + "저장 못하지롱");
        }
    }

    private <T extends Uploadable> List<T> parseBody(Sheet sheet, Class<T> targetDomain) {

        List<T> items = new ArrayList<>();

        for (Row row : sheet) {

            if (row.getRowNum() == START_INDEX) { // 최초 Row는 개별 값이 아닌 헤더값
                continue;
            }
            items.add(getItemFromRow(row, targetDomain));
        }
        return items;
    }

    private <T extends Uploadable> T getItemFromRow(Row row, Class<T> domain) {

        try {
            T item = domain.getDeclaredConstructor().newInstance();
            domain.getMethod(UPLOADABLE_METHOD_NAME, Row.class).invoke(item, row);
            return item;

        } catch (NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("임시");

        }
    }

    /**
     * 객체 리스트를 행에 기재한 엑셀 파일 생성
     *
     * @param items        대상 객체 리스트
     * @param targetDomain 객체 타입
     * @return 엑셀 파일을 byte[]로 반환
     */
    public <T> byte[] createExcel(List<T> items, Class<T> targetDomain) throws IOException {

        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(targetDomain.getName());

        renderHeaders(sheet.createRow(START_INDEX), getFields(targetDomain));  // 첫 행 헤더 정보 기입
        renderBody(sheet, items, targetDomain);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        wb.write(out);

        wb.close();
        return out.toByteArray();

    }

    private void renderHeaders(Row headerRow, List<String> fieldNames) {

        int cellIdx = START_INDEX;
        for (String header : fieldNames) {
            headerRow.createCell(cellIdx, CellType.STRING).setCellValue(header);
            cellIdx++;
        }
    }

    private <T> void renderBody(Sheet sheet, List<T> items, Class<T> targetDomain) {
        int rowIdx = START_INDEX;

        for (T item : items) {
            rowIdx++;
            int cellIdx = START_INDEX;
            Row row = sheet.createRow(rowIdx);

            for (Field field : targetDomain.getDeclaredFields()) {
                field.setAccessible(true);
                createRow(row, cellIdx, item, field);
                cellIdx++;
            }
        }
    }

    private <T> void createRow(Row row, int cellIdx, T item, Field field) {

        String type = item.getClass().getTypeName();

        try {
            switch (type) {
                case "int" -> row.createCell(cellIdx, CellType.NUMERIC).setCellValue((double) field.get(item));
                case "java.sql.Date" -> row.createCell(cellIdx, CellType.FORMULA).setCellValue((Date) field.get(item));
                default -> row.createCell(cellIdx, CellType.STRING).setCellValue(String.valueOf(field.get(item)));
            }
        } catch (IllegalAccessException e) {

            throw new RuntimeException("임시");
        }


    }

    private <T> List<String> getFields(Class<T> domain) {

        List<String> clazzFields = new ArrayList<>();

        Arrays.stream(domain.getDeclaredFields())
                .filter(e -> e.isAnnotationPresent(ExcelColumn.class))
                .forEach(e -> clazzFields.add(e.getAnnotation(ExcelColumn.class).name()));

        return clazzFields;
    }


}
