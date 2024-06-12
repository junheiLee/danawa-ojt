package com.ojt.first_be.constant;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS("성공적으로 처리되었습니다."),

    POSSIBLE("처리 가능합니다."),
    NOT_EXCEL_FILE("Excel 파일 확장자가 아닙니다."),
    NOT_HAVE_DOMAIN("해당 파일의 데이터는 저장할 수 있는 도메인이 아님."),
    NOT_TARGET_FORM("처리하고자 하는 유형의 레코드 형식이 아닙니다.");

    private final String message;

    ResultCode(String message) {
        this.message = message;
    }
}
