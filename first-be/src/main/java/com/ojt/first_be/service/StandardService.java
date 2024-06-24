package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.StandardProductDao;
import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.dto.request.Condition;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.dto.response.StandardProductList;
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

import static com.ojt.first_be.constant.ResultCode.SUCCESS;
import static com.ojt.first_be.constant.ResultCode.UPLOAD_RESULT;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StandardService {

    private final BatchService batchService;
    private final ExcelConverter excelConverter;
    private final StandardProductDao standardProductDao;

    /**
     * 기준 상품 정보가 담겨있는 엑셀 파일을 파싱해 DB에 저장하는 기능
     *
     * @param excelFile 기준 상품 정보가 담겨있는 엑셀 파일,
     *                      헤더: [대분류코드, 대분류명, 상품코드, 상품명, 묶음조건, 설명, 최저가, 평균가, 업체수]
     * @return 해당 정보 저장 성공 개수, 실패 개수 및 결과 코드
     */
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls) 확인
        if (!excelConverter.supports(excelFile.getOriginalFilename())) {
            throw new UnSupportedFileException(ResultCode.NOT_EXCEL_FILE);
        }

        List<StandardProduct> standardProducts
                = excelConverter.parseExcel(excelFile.getInputStream(), StandardProduct.class);

        SaveExcelResponse<Object> result = batchService.process(standardProducts, standardProductDao::saveAll);
        result.setResultCode(UPLOAD_RESULT);

        return result;
    }

    /**
     * 현재 출력 리스트를 엑셀 파일로 생성하는 기능
     *
     * @param condition 현재 페이지, 카테고리, 검색 정보
     * @return 페이지에 해당하는 엑셀 byte[]
     */
    public byte[] createExcelFile(Condition condition) {

        List<StandardProduct> standardProducts = getProducts(condition);
        return excelConverter.createExcel(standardProducts, StandardProduct.class);
    }

    /**
     * 해당 조건의 출력을 위해 페이징된 협력사 상품 리스트를 가져오는 기능
     *
     * @param condition 현재 페이지, 카테고리, 검색 정보
     * @return 페이지에 해당하는 리스트, 조건에 해당하는 아이템 개수, 결과 코드
     */
    public StandardProductList getStandardProducts(Condition condition) {

        List<StandardProduct> standardProducts = getProducts(condition);
        int totalItemsCount = standardProductDao.countAll(condition);

        return StandardProductList.builder()
                .resultCode(SUCCESS.name())
                .message(SUCCESS.getMessage())
                .totalItemsCount(totalItemsCount)
                .products(standardProducts)
                .build();
    }

    private List<StandardProduct> getProducts(Condition condition) {

        return standardProductDao.findAll(condition);
    }

}
