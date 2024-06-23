package com.ojt.first_be.service;

import com.ojt.first_be.domain.search.Condition;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.dto.response.StandardProductList;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StandardService {

    /**
     * 기준 상품 정보가 담겨있는 엑셀 파일을 파싱해 DB에 저장하는 기능
     *
     * @param multipartFile 기준 상품 정보가 담겨있는 엑셀 파일,
     *                      헤더: [대분류코드, 대분류명, 상품코드, 상품명, 묶음조건, 설명, 최저가, 평균가, 업체수]
     * @return 해당 정보 저장 성공 개수, 실패 개수 및 결과 코드
     */
    SaveExcelResponse<Object> saveExcelData(MultipartFile multipartFile) throws IOException;

    /**
     * 현재 출력 리스트를 엑셀 파일로 생성하는 기능
     *
     * @param condition 현재 페이지, 카테고리, 검색 정보
     * @return 페이지에 해당하는 엑셀 byte[]
     */
    byte[] createExcelFile(Condition condition);

    /**
     * 해당 조건의 출력을 위해 페이징된 협력사 상품 리스트를 가져오는 기능
     *
     * @param condition 현재 페이지, 카테고리, 검색 정보
     * @return 페이지에 해당하는 리스트, 조건에 해당하는 아이템 개수, 결과 코드
     */
    StandardProductList getStandardProducts(Condition condition);

}
