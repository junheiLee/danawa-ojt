package com.ojt.first_be.service;

import com.ojt.first_be.dao.PartnerProductDao;
import com.ojt.first_be.domain.PartnerProduct;
import com.ojt.first_be.dto.response.PartnerProductList;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.util.batch.BatchUtil;
import com.ojt.first_be.util.excel.ExcelConverter;
import com.ojt.first_be.util.paging.PagingUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ojt.first_be.constant.Common.OUTPUT_LIST_LIMIT_SIZE;
import static com.ojt.first_be.constant.ResultCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PartnerProductServiceImpl implements PartnerProductService {

    private final BatchUtil batchUtil;
    private final ExcelConverter excelConverter;
    private final PartnerProductDao partnerProductDao;

    @Override
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls) 확인
        if (!excelConverter.supports(excelFile.getOriginalFilename())) {
            throw new RuntimeException("임시");
        }

        List<PartnerProduct> partnerProducts
                = excelConverter.parse(excelFile.getInputStream(), PartnerProduct.class);

        return batchUtil.process(partnerProducts, partnerProductDao::saveAll, partnerProductDao::save);
    }

    @Override
    public PartnerProductList getPartnerProducts(int page, boolean isTotalPageRequired) {

        List<PartnerProduct> partnerProducts
                = partnerProductDao.findAll(OUTPUT_LIST_LIMIT_SIZE, PagingUtil.calOffset(page));

        // 총 페이지 수를 요구하면 DAO 에서 Count, 필요하지 않으면 null
        Integer totalPage = PagingUtil.getTotalPage(isTotalPageRequired, partnerProductDao::countAll);

        return PartnerProductList.builder()
                .resultCode(SUCCESS.name())
                .message(SUCCESS.getMessage())
                .totalPage(totalPage)
                .products(partnerProducts)
                .build();
    }
}
