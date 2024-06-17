package com.ojt.first_be.service;

import com.ojt.first_be.dto.response.PageCount;
import com.ojt.first_be.dto.response.PartnerProductList;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PartnerProductService {

    /**
     * 파트너 상품 정보가 담겨있는 엑셀 파일을 파싱해 DB에 저장하는 기능
     *
     * @param multipartFile 파트너 상품 정보가 담겨있는 엑셀 파일,
     *                      헤더: [대분류코드, 대분류명, 협력사코드, 상품코드, 상품명, PC가, 모바일가, 등록일, url, 이미지url]
     * @return 해당 정보 저장 성공 개수, 실패 개수 및 실패한 객체 정보 리스트
     */
    SaveExcelResponse<Object> saveExcelData(MultipartFile multipartFile) throws IOException;

    /**
     * 현재 페이지를 엑셀 파일로 생성하는 기능
     *
     * @param page 현재 페이지
     * @return 페이지에 해당하는 엑셀 byte[]
     */
    byte[] createExcelFile(int page) throws IOException;

    /**
     * 페이징 처리를 위한 count 기능
     *
     * @return page 개수와 총 리스트 개수 반환
     */
    PageCount getCountAboutPage();

    /**
     * 출력을 위해 페이징된 협력사 상품 리스트를 가져오는 기능
     *
     * @param page 현재 페이지
     * @return 페이지에 해당하는 리스트
     */
    PartnerProductList getPartnerProducts(int page);

}
