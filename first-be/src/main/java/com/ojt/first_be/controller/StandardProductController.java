package com.ojt.first_be.controller;

import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.dto.response.StandardProductList;
import com.ojt.first_be.service.StandardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/standard-products")
@RestController
public class StandardProductController {

    private final StandardService standardService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SaveExcelResponse<Object> uploadExcelData(@RequestParam MultipartFile excelFile) throws IOException {

        return standardService.saveExcelData(excelFile);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public StandardProductList getStandardProducts(@RequestParam(defaultValue = "1") int page,
                                                   @RequestParam(defaultValue = "false") boolean isTotalPageRequired) {

        return standardService.getStandardProducts(page, isTotalPageRequired);
    }
}
