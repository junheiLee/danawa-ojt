package com.ojt.first_be.service;

import com.ojt.first_be.dto.response.CategoryList;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CategoryService {

    /**
     * 카테고리 정보가 담겨있는 엑셀 파일을 파싱해 DB에 저장하는 기능
     *
     * @param multipartFile 카테고리 정보가 담겨있는 엑셀 파일, 헤더: [대분류코드, 대분류명]
     * @return 해당 정보 저장 성공 개수, 실패 개수 및 결과 코드를 담은 객체
     */
    SaveExcelResponse<Object> saveExcelData(MultipartFile multipartFile) throws IOException;

    /**
     * 전체 카테고리 정보 리스트 반환 기능
     *
     * @return 전체 카테고리 리스트와 결과 코드를 담은 객체
     */
    CategoryList getCategories();
}
