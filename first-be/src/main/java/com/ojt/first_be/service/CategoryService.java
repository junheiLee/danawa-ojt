package com.ojt.first_be.service;

import com.ojt.first_be.dto.response.SaveExcelResponse;

import java.io.IOException;
import java.io.InputStream;

public interface CategoryService {

    SaveExcelResponse saveExcelData(String fileName, InputStream fileInputStream) throws IOException;
}
