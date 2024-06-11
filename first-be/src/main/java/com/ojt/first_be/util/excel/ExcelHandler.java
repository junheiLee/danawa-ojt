package com.ojt.first_be.util.excel;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.domain.Uploadable;
import com.ojt.first_be.domain.UploadableFileForm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
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
     * 해당 파일이 Excel 확장자(.xlsx, .xls)인지, 파싱해서 저장할 수 있는 형식인지 확인
     *
     * @param targetFile 대상 파일
     * @param fileForm   파싱해서 저장할 형식
     * @return 결과 코드
     */
    public static ResultCode check(MultipartFile targetFile, UploadableFileForm fileForm) throws IOException {

        if (!isExcel(targetFile.getOriginalFilename())) {
            return ResultCode.NOT_EXCEL_FILE;
        }

        List<String> headers = getHeaders(targetFile);
        if (!fileForm.canSave(headers)) {
            return ResultCode.NOT_TARGET_FORM;
        }

        return ResultCode.POSSIBLE;
    }

    private static boolean isExcel(String fileName) {

        String extension = FilenameUtils.getExtension(fileName);
        return Arrays.asList(EXCEL_EXTENSIONS).contains(extension);
    }

    private static List<String> getHeaders(MultipartFile targetFile) throws IOException {

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
    public static <T extends Uploadable> List<T> getObjectList(InputStream inputStream,
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
        wb.close();
        return items;
    }

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
