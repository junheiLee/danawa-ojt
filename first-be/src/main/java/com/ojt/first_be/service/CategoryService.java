package com.ojt.first_be.service;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.dao.CategoryDao;
import com.ojt.first_be.domain.Category;
import com.ojt.first_be.dto.response.CategoryList;
import com.ojt.first_be.dto.response.SaveExcelResponse;
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

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final ExcelConverter excelConverter;
    private final BatchService batchService;
    private final CategoryDao categoryDao;

    /**
     * 카테고리 정보가 담겨있는 엑셀 파일을 파싱해 DB에 저장하는 기능
     *
     * @param excelFile 카테고리 정보가 담겨있는 엑셀 파일, 헤더: [대분류코드, 대분류명]
     * @return 해당 정보 저장 성공 개수, 실패 개수 및 결과 코드를 담은 객체
     */
    public SaveExcelResponse<Object> saveExcelData(MultipartFile excelFile) throws IOException {

        // 파일이 Excel 확장자(.xlsx, .xls) 확인
        if (!excelConverter.supports(excelFile.getOriginalFilename())) {
            throw new UnSupportedFileException(ResultCode.NOT_EXCEL_FILE);
        }

        List<Category> categories = excelConverter.parseExcel(excelFile.getInputStream(), Category.class);

        SaveExcelResponse<Object> result = batchService.process(categories, categoryDao::saveAll);
        result.setResultCode(ResultCode.UPLOAD_RESULT);

        return result;
    }

    /**
     * 전체 카테고리 정보 리스트 반환 기능
     *
     * @return 전체 카테고리 리스트와 결과 코드를 담은 객체
     */
    public CategoryList getCategories() {

        List<Category> categories = categoryDao.findAll();

        return CategoryList.builder()
                .resultCode(ResultCode.SUCCESS)
                .categories(categories)
                .build();
    }
}
