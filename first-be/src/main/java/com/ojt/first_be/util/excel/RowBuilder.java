package com.ojt.first_be.util.excel;

import com.ojt.first_be.domain.StandardProduct;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

public class RowBuilder {

    public void createStandardProductRow(StandardProduct product, Row row) {

        row.createCell(0, CellType.NUMERIC).setCellValue(product.getCategoryCode());
        row.createCell(1, CellType.STRING).setCellValue(product.getCategoryName());
        row.createCell(2, CellType.NUMERIC).setCellValue(product.getCode());
        row.createCell(3, CellType.STRING).setCellValue(product.getName());
        row.createCell(4, CellType.STRING).setCellValue(product.getBundleCondition());
        row.createCell(5, CellType.STRING).setCellValue(product.getDescription());
        row.createCell(6, CellType.NUMERIC).setCellValue(product.getLowestPrice());
        row.createCell(7, CellType.NUMERIC).setCellValue(product.getAveragePrice());
        row.createCell(8, CellType.NUMERIC).setCellValue(product.getPartnerCount());
    }
}
