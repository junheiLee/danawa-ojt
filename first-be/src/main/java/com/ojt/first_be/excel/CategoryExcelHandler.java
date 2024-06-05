package com.ojt.first_be.excel;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class CategoryExcelHandler implements ExcelHandler {

    @Override
    public <T> List<T> toObjectList(File file) {
        return List.of();
    }
}
