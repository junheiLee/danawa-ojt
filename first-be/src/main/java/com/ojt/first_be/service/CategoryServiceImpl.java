package com.ojt.first_be.service;

import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.excel.CategoryExcelHandler;
import com.ojt.first_be.util.excel.PostExcelHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.ojt.first_be.domain.UploadableFileForm.CATEGORY;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryExcelHandler categoryExcelHandler;

    @Override
    public SaveExcelResponse saveExcelData(String fileName, InputStream fileInputStream) throws IOException {

        validFile(fileName, fileInputStream);

        return SaveExcelResponse.builder().build();
    }

    private static void validFile(String fileName, InputStream fileInputStream) throws IOException {

        if (!PostExcelHandler.canParse(fileName)) {
            throw new RuntimeException("임시" + "확장자가 다르다는 메시지");
        }

        List<String> headers = PostExcelHandler.getHeaders(fileInputStream);

        if (!CATEGORY.canSave(headers)) {
            throw new RuntimeException("임시" + "카테고리에 해당하는 엑셀 폼이 아님");
        }

    }
}
