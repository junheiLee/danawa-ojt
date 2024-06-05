package com.ojt.first_be.excel;

import java.io.File;
import java.util.List;

public class StandardProductExcelHandler implements ExcelHandler {

    @Override
    public <T> List<T> toObjectList(File file) {
        return List.of();
    }
}