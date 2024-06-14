package com.ojt.first_be.service;

import com.ojt.first_be.dao.StandardProductDao;
import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.dto.response.StandardProductList;
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
import static com.ojt.first_be.constant.ResultCode.CREATED;
import static com.ojt.first_be.constant.ResultCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StandardServiceImpl implements StandardService {

    private final BatchUtil batchUtil;
    private final ExcelConverter excelConverter;
    private final StandardProductDao standardProductDao;

    @Override
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls) 확인
        if (!excelConverter.supports(excelFile.getOriginalFilename())) {
            throw new RuntimeException("임시");
        }

        List<StandardProduct> standardProducts
                = excelConverter.parseExcel(excelFile.getInputStream(), StandardProduct.class);

        SaveExcelResponse<Object> result = batchUtil.process(standardProducts, standardProductDao::saveAll, standardProductDao::save);
        result.setResultCode(CREATED);

        return result;
    }

    @Override
    public StandardProductList getStandardProducts(int page, boolean isTotalPageRequired) {

        List<StandardProduct> standardProducts = getProducts(page);

        // 총 페이지 수를 요구하면 DAO 에서 Count, 필요하지 않으면 null
        Integer totalPage = PagingUtil.getTotalPage(isTotalPageRequired, standardProductDao::countAll);

        return StandardProductList.builder()
                .resultCode(SUCCESS.name())
                .message(SUCCESS.getMessage())
                .totalPage(totalPage)
                .products(standardProducts)
                .build();
    }

    @Override
    public byte[] createExcelFile(int page) throws IOException {

        List<StandardProduct> standardProducts = getProducts(page);
        return excelConverter.createExcel(standardProducts, StandardProduct.class);
    }

    private List<StandardProduct> getProducts(int page) {

        return standardProductDao.findAll(OUTPUT_LIST_LIMIT_SIZE, PagingUtil.calOffset(page));
    }
}
