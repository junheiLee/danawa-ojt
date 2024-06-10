package com.ojt.first_be.service;

import com.ojt.first_be.dao.CategoryDao;
import com.ojt.first_be.domain.Category;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.util.batch.BatchUtil;
import com.ojt.first_be.util.excel.CategoryExcelHandler;
import com.ojt.first_be.util.excel.ExcelHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ojt.first_be.domain.UploadableFileForm.CATEGORY;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryExcelHandler categoryExcelHandler;
    private final CategoryDao categoryDao;
    private final BatchUtil batchUtil;

    @Transactional
    @Override
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        validFile(excelFile);
        List<Category> categories = categoryExcelHandler.getObjectList(excelFile.getInputStream(), categoryExcelHandler::buildFormExcel);

        return batchUtil.process(categories, categoryDao::saveCategoryList, categoryDao::saveCategory);
    }

    private static void validFile(MultipartFile targetFile) throws IOException {

        if (!ExcelHandler.canParse(targetFile.getOriginalFilename())) {
            throw new RuntimeException("임시" + "확장자가 다르다는 메시지");
        }

        List<String> headers = ExcelHandler.getHeaders(targetFile);

        if (!CATEGORY.canSave(headers)) {
            throw new RuntimeException("임시" + "카테고리에 해당하는 엑셀 폼이 아님");
        }

    }
}
