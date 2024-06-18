package com.ojt.first_be.exception.handler;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dto.response.BaseResponse;
import com.ojt.first_be.exception.ExcelInternalException;
import com.ojt.first_be.exception.custom.NoExcelColumnAnnotationsException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ooxml.POIXMLException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(POIXMLException.class)
    public BaseResponse spreadSheetExHandle(POIXMLException e) {

        log.error("[ExcelException] 스프레드 시트로 업로드={}", e.getMessage());
        return new BaseResponse(ResultCode.NOT_INTEGRATED_EXCEL_FILE);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NoExcelColumnAnnotationsException.class)
    public BaseResponse excelHeaderExHandle(NoExcelColumnAnnotationsException e) {

        log.error("[ExcelException] excel 파일 헤더와 업로드하고자 하는 도메인이 다름");
        return new BaseResponse(e.getResultCode());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ExcelInternalException.class)
    public void excelHeaderExHandle(ExcelInternalException e) {

        log.error("[ExcelException] 해당 없음", e);
    }
}
