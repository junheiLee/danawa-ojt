package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.StandardProductDao;
import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.dto.response.PageCount;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.dto.response.StandardProductList;
import com.ojt.first_be.exception.custom.UnSupportedFileException;
import com.ojt.first_be.util.batch.BatchService;
import com.ojt.first_be.util.excel.ExcelConverter;
import com.ojt.first_be.util.paging.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ojt.first_be.constant.Common.OUTPUT_LIST_LIMIT_SIZE;
import static com.ojt.first_be.constant.ResultCode.UPLOAD_RESULT;
import static com.ojt.first_be.constant.ResultCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StandardServiceImpl implements StandardService {

    private final BatchService batchService;
    private final ExcelConverter excelConverter;
    private final StandardProductDao standardProductDao;

    @Override
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

    @Override
    public byte[] createExcelFile(int page) {

        List<StandardProduct> standardProducts = getProducts(page);
        return excelConverter.createExcel(standardProducts, StandardProduct.class);
    }

    @Override
    public PageCount getCountAboutPage() {

        return Paging.getTotalPage(standardProductDao::countAll);
    }

    @Override
    public StandardProductList getStandardProducts(int page) {

        List<StandardProduct> standardProducts = getProducts(page);

        return StandardProductList.builder()
                .resultCode(SUCCESS.name())
                .message(SUCCESS.getMessage())
                .products(standardProducts)
                .build();
    }

    private List<StandardProduct> getProducts(int page) {

        return standardProductDao.findAll(OUTPUT_LIST_LIMIT_SIZE, Paging.calOffset(page));
    }

}
