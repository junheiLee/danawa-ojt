package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.PartnerProductDao;
import com.ojt.first_be.domain.PartnerProduct;
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
import static com.ojt.first_be.domain.UploadableFileForm.PARTNER_PRODUCT;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PartnerProductServiceImpl implements PartnerProductService {

    private final BatchUtil batchUtil;
    private final BuilderFromRow builderFromRow;
    private final PartnerProductDao partnerProductDao;

    @Override
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls)인지, 협력사 상품 파일인지 확인
        ResultCode resultCode = ExcelHandler.check(excelFile, PARTNER_PRODUCT);
        if (resultCode != POSSIBLE) {
            throw new RuntimeException("임시" + resultCode.getMessage());
        }

        List<PartnerProduct> partnerProducts
                = ExcelHandler.getObjectList(excelFile.getInputStream(), builderFromRow::buildPartnerProduct);

        return batchUtil.process(partnerProducts, partnerProductDao::saveAll, partnerProductDao::save);
    }
}
