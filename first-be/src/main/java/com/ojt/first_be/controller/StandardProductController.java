package com.ojt.first_be.controller;

import com.ojt.first_be.dto.response.PageCount;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.dto.response.StandardProductList;
import com.ojt.first_be.service.StandardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/standard-products")
@RestController
public class StandardProductController {

    public static final String PRODUCTS_XLSX = "attachment; filename=standard_products.xlsx";
    private final StandardService standardService;

    @ResponseStatus(HttpStatus.MULTI_STATUS)
    @PostMapping("/excel-upload")
    public SaveExcelResponse<Object> uploadExcelData(@RequestParam MultipartFile excelFile) throws IOException {

        return standardService.saveExcelData(excelFile);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcel(@RequestParam(defaultValue = "1") int page) throws IOException {

        byte[] excelBytes = standardService.createExcelFile(page);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, PRODUCTS_XLSX);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/page-info")
    public PageCount getCountAboutPage() {

        return standardService.getCountAboutPage();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public StandardProductList getStandardProducts(@RequestParam(defaultValue = "1") int page) {

        return standardService.getStandardProducts(page);
    }

}
