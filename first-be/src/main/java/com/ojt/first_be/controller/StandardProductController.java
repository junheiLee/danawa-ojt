package com.ojt.first_be.controller;

import com.ojt.first_be.domain.search.Condition;
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

    public static final String ATTACHMENT = "attachment; filename=standard_products.xlsx";
    private final StandardService standardService;

    @ResponseStatus(HttpStatus.MULTI_STATUS)
    @PostMapping("/excel-upload")
    public SaveExcelResponse<Object> uploadExcelData(@RequestParam MultipartFile excelFile) throws IOException {

        return standardService.saveExcelData(excelFile);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcel(@ModelAttribute Condition condition) {

        byte[] excelBytes = standardService.createExcelFile(condition);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public StandardProductList getStandardProducts(@ModelAttribute Condition condition) {

        return standardService.getStandardProducts(condition);
    }

}
