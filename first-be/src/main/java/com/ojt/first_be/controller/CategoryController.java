package com.ojt.first_be.controller;

import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<SaveExcelResponse<Object>> uploadExcelData(@RequestParam MultipartFile excelFile) throws IOException {

        SaveExcelResponse<Object> responseBody = categoryService.saveExcelData(excelFile);

        if (responseBody.getSuccessCount() > 0) {
            return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

}
