package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.CategoryDao;
import com.ojt.first_be.domain.Category;
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
import static com.ojt.first_be.domain.UploadableFileForm.CATEGORY;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl implements CategoryService {

    private final BuilderFromRow builderFromRow;
    private final CategoryDao categoryDao;

    @Transactional
    @Override
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        ResultCode resultCode = ExcelHandler.check(excelFile, CATEGORY);
        if (resultCode != POSSIBLE) {
            throw new RuntimeException("임시" + resultCode.getMessage());
        }

        List<Category> categories
                = ExcelHandler.getObjectList(excelFile.getInputStream(), builderFromRow::buildCategory);

        return BatchUtil.process(categories, categoryDao::saveCategoryList, categoryDao::saveCategory);
    }

}
