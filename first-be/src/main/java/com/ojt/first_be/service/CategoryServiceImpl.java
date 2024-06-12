package com.ojt.first_be.service;

import com.ojt.first_be.dao.CategoryDao;
import com.ojt.first_be.domain.Category;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.util.batch.BatchUtil;
import com.ojt.first_be.util.excel.ExcelConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryServiceImpl implements CategoryService {

    private final BatchUtil batchUtil;
    private final ExcelConverter excelConverter;
    private final CategoryDao categoryDao;

    @Override
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls) 확인
        if (!excelConverter.supports(excelFile.getOriginalFilename())) {
            throw new RuntimeException("임시");
        }

        List<Category> categories
                = excelConverter.parseExcel(excelFile.getInputStream(), Category.class);

        return batchUtil.process(categories, categoryDao::saveAll, categoryDao::save);
    }
}
