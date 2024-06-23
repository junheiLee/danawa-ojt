package com.ojt.first_be.service;

import com.ojt.first_be.dto.response.SaveExcelResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PartnerService {

    /**
     * 파트너 정보가 담겨있는 엑셀 파일을 파싱해 DB에 저장하는 기능
     *
     * @param multipartFile 파트너 상품 정보가 담겨있는 엑셀 파일,
     *                      헤더: [협력사코드, 협력사명]
     * @return 해당 정보 저장 성공 개수, 실패 개수 및 결과 코드
     */
    SaveExcelResponse<Object> saveExcelData(MultipartFile multipartFile) throws IOException;
}
