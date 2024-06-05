package com.ojt.first_be.service;

import com.ojt.first_be.domain.Category;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.excel.CategoryExcelHandler;
import com.ojt.first_be.util.excel.PostExcelHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.ojt.first_be.domain.UploadableFileForm.CATEGORY;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryExcelHandler categoryExcelHandler;

    @Override
    public SaveExcelResponse saveExcelData( MultipartFile excelFile) throws IOException {

        validFile(excelFile);
        List<Category> categories = categoryExcelHandler.toObjectList(excelFile.getInputStream());
        log.info("categories={}", categories.toString());
        return SaveExcelResponse.builder().build();
    }
    
    private static void validFile(MultipartFile targetFile) throws IOException {

        if (!PostExcelHandler.canParse(targetFile.getOriginalFilename())) {
            throw new RuntimeException("임시" + "확장자가 다르다는 메시지");
        }

        List<String> headers = PostExcelHandler.getHeaders(targetFile);

        if (!CATEGORY.canSave(headers)) {
            throw new RuntimeException("임시" + "카테고리에 해당하는 엑셀 폼이 아님");
        }

    }
}
