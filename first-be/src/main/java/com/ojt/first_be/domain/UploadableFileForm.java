package com.ojt.first_be.domain;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 업로드 시 선택한 조건
 * 해당 조건의 파일이 가지고 있어야 하는 헤더(파일의 첫 번째 줄)
 * 해당 파일을 파싱, 렌더할 수 있는 Handler
 */
@Getter
public enum UploadableFileForm {

    CATEGORY(
            "카테고리",
            Arrays.asList("대분류코드", "대분류명")),
    STANDARD_PRODUCT(
            "기준상품",
            Arrays.asList("대분류코드", "대분류명", "상품코드", "상품명", "묶음조건", "설명", "최저가", "평균가", "업체수")),
    PARTNER(
            "협력사",
            Arrays.asList("협력사코드", "협력사명")),
    PARTNER_PRODUCT(
            "협력사상품",
            Arrays.asList("대분류코드", "대분류명", "협력사코드", "상품코드", "상품명", "PC가", "모바일가", "등록일", "url", "이미지url"));

    private final String condition;
    private final List<String> headers;

    UploadableFileForm(String condition, List<String> headers) {

        this.condition = condition;
        this.headers = headers;
    }

    public boolean canSave(List<String> headers) {

        return this.headers.equals(headers);
    }

}
