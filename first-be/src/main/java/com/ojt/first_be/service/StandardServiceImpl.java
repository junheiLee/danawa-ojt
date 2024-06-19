package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.StandardProductDao;
import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.domain.search.Condition;
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
    public byte[] createExcelFile(Condition condition) {

        List<StandardProduct> standardProducts = getProducts(condition);
        return excelConverter.createExcel(standardProducts, StandardProduct.class);
    }

    @Override
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
