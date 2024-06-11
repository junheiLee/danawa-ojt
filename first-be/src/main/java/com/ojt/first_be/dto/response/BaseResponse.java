package com.ojt.first_be.dto.response;

import lombok.Getter;

/**
 * JSON 형식의 Response에 반드시 들어가야하는 항목
 * code와 message만 들어갈 경우 생성자로 객체를 생성
 */
@Getter
public class BaseResponse {

    private String resultCode;
    private String message;

    public BaseResponse(String resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }
}
