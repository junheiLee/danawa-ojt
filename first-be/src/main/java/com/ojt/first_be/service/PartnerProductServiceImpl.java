package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.PartnerProductDao;
import com.ojt.first_be.domain.PartnerProduct;
import com.ojt.first_be.dto.response.PageCount;
import com.ojt.first_be.dto.response.PartnerProductList;
import com.ojt.first_be.dto.response.SaveExcelResponse;
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
import static com.ojt.first_be.constant.ResultCode.CREATED;
import static com.ojt.first_be.constant.ResultCode.SUCCESS;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PartnerProductServiceImpl implements PartnerProductService {

    private final BatchService batchService;
    private final ExcelConverter excelConverter;
    private final PartnerProductDao partnerProductDao;

    @Override
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls) 확인
        if (!excelConverter.supports(excelFile.getOriginalFilename())) {
            throw new UnSupportedFileException(ResultCode.NOT_EXCEL_FILE);
        }

        List<PartnerProduct> partnerProducts
                = excelConverter.parseExcel(excelFile.getInputStream(), PartnerProduct.class);

        SaveExcelResponse<Object> result = batchService.process(partnerProducts, partnerProductDao::saveAll);
        result.setResultCode(CREATED);

        return result;
    }

    @Override
    public byte[] createExcelFile(int page) throws IOException {

        List<PartnerProduct> partnerProducts = getProducts(page);
        return excelConverter.createExcel(partnerProducts, PartnerProduct.class);
    }

    @Override
    public PageCount getCountAboutPage() {

        return Paging.getTotalPage(partnerProductDao::countAll);
    }

    @Override
    public PartnerProductList getPartnerProducts(int page) {

        List<PartnerProduct> partnerProducts = getProducts(page);

        return PartnerProductList.builder()
                .resultCode(SUCCESS.name())
                .message(SUCCESS.getMessage())
                .products(partnerProducts)
                .build();
    }

    private List<PartnerProduct> getProducts(int page) {

        return partnerProductDao.findAll(OUTPUT_LIST_LIMIT_SIZE, Paging.calOffset(page));
    }

}
