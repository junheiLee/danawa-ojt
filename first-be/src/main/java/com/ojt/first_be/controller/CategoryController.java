package com.ojt.first_be.controller;

import com.ojt.first_be.dto.response.CategoryList;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.MULTI_STATUS)
    @PostMapping("/upload/excel")
    public SaveExcelResponse<Object> uploadExcel(@RequestParam MultipartFile excelFile) throws IOException {

        return categoryService.saveExcelData(excelFile);
    }

    @GetMapping
    public CategoryList getCategories() {
        return categoryService.getCategories();
    }
}
