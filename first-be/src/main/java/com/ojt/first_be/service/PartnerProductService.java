package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.PartnerProductDao;
import com.ojt.first_be.domain.PartnerProduct;
import com.ojt.first_be.dto.request.Condition;
import com.ojt.first_be.dto.response.PartnerProductList;
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

import static com.ojt.first_be.constant.ResultCode.SUCCESS;
import static com.ojt.first_be.constant.ResultCode.UPLOAD_RESULT;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PartnerProductService {

    private final ExcelConverter excelConverter;
    private final BatchService batchService;
    private final PartnerProductDao partnerProductDao;

    /**
     * 파트너 상품 정보가 담겨있는 엑셀 파일을 파싱해 DB에 저장하는 기능
     *
     * @param excelFile 파트너 상품 정보가 담겨있는 엑셀 파일,
     *                      헤더: [대분류코드, 대분류명, 협력사코드, 상품코드, 상품명, PC가, 모바일가, 등록일, url, 이미지url]
     * @return 해당 정보 저장 성공 개수, 실패 개수 및 결과 코드를 담은 객체
     */
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls) 확인
        if (!excelConverter.supports(excelFile.getOriginalFilename())) {
            throw new UnSupportedFileException(ResultCode.NOT_EXCEL_FILE);
        }

        List<PartnerProduct> partnerProducts
                = excelConverter.parseExcel(excelFile.getInputStream(), PartnerProduct.class);

        SaveExcelResponse<Object> result = batchService.process(partnerProducts, partnerProductDao::saveAll);
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

        List<PartnerProduct> partnerProducts = getProducts(condition);
        return excelConverter.createExcel(partnerProducts, PartnerProduct.class);
    }

    /**
     * 해당 조건의 출력을 위해 페이징된 협력사 상품 리스트를 가져오는 기능
     *
     * @param condition 현재 페이지, 카테고리, 검색 정보
     * @return 페이지에 해당하는 리스트, 조건에 해당하는 아이템 개수, 결과 코드
     */
    public PartnerProductList getPartnerProducts(Condition condition) {

        List<PartnerProduct> partnerProducts = getProducts(condition);
        int totalItemsCount = partnerProductDao.countAll(condition);

        return PartnerProductList.builder()
                .resultCode(SUCCESS.name())
                .message(SUCCESS.getMessage())
                .totalItemsCount(totalItemsCount)
                .products(partnerProducts)
                .build();
    }

    private List<PartnerProduct> getProducts(Condition condition) {

        return partnerProductDao.findAll(condition);
    }

}
