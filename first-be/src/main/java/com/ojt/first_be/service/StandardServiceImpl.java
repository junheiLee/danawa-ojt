package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.StandardProductDao;
import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.util.batch.BatchUtil;
import com.ojt.first_be.util.excel.BuilderFromRow;
import com.ojt.first_be.util.excel.ExcelHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ojt.first_be.constant.ResultCode.POSSIBLE;
import static com.ojt.first_be.domain.UploadableFileForm.STANDARD_PRODUCT;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class StandardServiceImpl implements StandardService {

    private final BatchUtil batchUtil;
    private final BuilderFromRow builderFromRow;
    private final StandardProductDao standardProductDao;

    @Override
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls)인지, 기준 상품 파일인지 확인
        ResultCode resultCode = ExcelHandler.check(excelFile, STANDARD_PRODUCT);
        if (resultCode != POSSIBLE) {
            throw new RuntimeException("임시" + resultCode.getMessage());
        }

        List<StandardProduct> standardProducts
                = ExcelHandler.getObjectList(excelFile.getInputStream(), builderFromRow::buildStandardProduct);

        return batchUtil.process(standardProducts, standardProductDao::saveAll, standardProductDao::save);
    }
}
