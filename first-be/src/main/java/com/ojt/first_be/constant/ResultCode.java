package com.ojt.first_be.constant;

import lombok.Getter;

@Getter
public enum ResultCode {

    POSSIBLE("처리 가능합니다."),

    SUCCESS("성공적으로 처리되었습니다."),
    CREATED("생성: %d개, 실패: %d개"),

    NOT_EXCEL_FILE("Excel 파일 확장자가 아닙니다."),
    NOT_INTEGRATED_EXCEL_FILE("스프레드 시트는 업로드할 수 없습니다. \nExcel 통합 문서로 저장해주세요."),
    NOT_HAVE_DOMAIN("업로드하는 파일의 옵션을 확인해주세요. \n현재 옵션: ");

    private final String message;

    ResultCode(String message) {
        this.message = message;
    }
}
