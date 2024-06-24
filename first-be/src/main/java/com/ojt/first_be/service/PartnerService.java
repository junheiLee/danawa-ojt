package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.PartnerDao;
import com.ojt.first_be.domain.Partner;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.exception.custom.UnSupportedFileException;
import com.ojt.first_be.util.batch.BatchService;
import com.ojt.first_be.util.excel.ExcelConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ojt.first_be.constant.ResultCode.UPLOAD_RESULT;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PartnerService {

    private final BatchService batchService;
    private final ExcelConverter excelConverter;
    private final PartnerDao partnerDao;

    /**
     * 파트너 정보가 담겨있는 엑셀 파일을 파싱해 DB에 저장하는 기능
     *
     * @param excelFile 파트너 상품 정보가 담겨있는 엑셀 파일,
     *                      헤더: [협력사코드, 협력사명]
     * @return 해당 정보 저장 성공 개수, 실패 개수 및 결과 코드
     */
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls) 확인
        if (!excelConverter.supports(excelFile.getOriginalFilename())) {
            throw new UnSupportedFileException(ResultCode.NOT_EXCEL_FILE);
        }

        List<Partner> partners = excelConverter.parseExcel(excelFile.getInputStream(), Partner.class);

        SaveExcelResponse<Object> result = batchService.process(partners, partnerDao::saveAll);
        result.setResultCode(UPLOAD_RESULT);

        return result;
    }
}
