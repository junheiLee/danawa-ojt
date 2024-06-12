package com.ojt.first_be.controller;

import com.ojt.first_be.dto.response.PartnerProductList;
import com.ojt.first_be.dto.response.SaveExcelResponse;
import com.ojt.first_be.service.PartnerProductService;
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
@RequestMapping("/partner-products")
@RestController
public class PartnerProductController {

    public static final String PRODUCTS_XLSX = "attachment; filename=standard_products.xlsx";

    private final PartnerProductService partnerProductService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public SaveExcelResponse<Object> uploadExcelData(@RequestParam MultipartFile excelFile) throws IOException {

        return partnerProductService.saveExcelData(excelFile);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public PartnerProductList getStandardProducts(@RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "false") boolean isTotalPageRequired) {

        return partnerProductService.getPartnerProducts(page, isTotalPageRequired);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcel(@RequestParam(defaultValue = "1") int page) throws IOException {

        byte[] excelBytes = partnerProductService.createExcelFile(page);

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, PRODUCTS_XLSX);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);

    }
}
