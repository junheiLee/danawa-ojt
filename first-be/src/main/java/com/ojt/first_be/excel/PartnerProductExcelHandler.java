package com.ojt.first_be.excel;

import java.io.InputStream;
import java.util.List;

public class PartnerProductExcelHandler implements ExcelHandler {

    @Override
    public <T> List<T> toObjectList(InputStream inputStream) {
        return List.of();
    }
}