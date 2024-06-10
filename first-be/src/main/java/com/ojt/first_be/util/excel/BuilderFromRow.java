package com.ojt.first_be.util.excel;

import com.ojt.first_be.domain.Category;
import com.ojt.first_be.domain.Partner;
import com.ojt.first_be.domain.PartnerProduct;
import com.ojt.first_be.domain.StandardProduct;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class BuilderFromRow {

    public Category buildCategory(Row row) {

        return Category.builder()
                .code((int) row.getCell(0).getNumericCellValue())
                .name(row.getCell(1).getStringCellValue())
                .build();
    }

    public Partner buildPartner(Row row) {

        return Partner.builder()
                .code(row.getCell(0).getStringCellValue().trim())
                .name(row.getCell(1).getStringCellValue())
                .build();
    }

    public PartnerProduct buildPartnerProduct(Row row) {

        return PartnerProduct.builder()

                .categoryCode((int) row.getCell(0).getNumericCellValue())
                .partnerCode(returnStringValue(row.getCell(2)))

                .code(returnStringValue(row.getCell(3)))
                .name(returnStringValue(row.getCell(4)))

                .pcPrice((int) row.getCell(5).getNumericCellValue())
                .mobilePrice((int) row.getCell(6).getNumericCellValue())

                .createdAt(new Date(row.getCell(7).getDateCellValue().getTime()))
                .url(returnStringValue(row.getCell(8)))
                .imageUrl(returnStringValue(row.getCell(9)))

                .build();
    }

    public StandardProduct buildStandardProduct(Row row) {

        return StandardProduct.builder()

                .categoryCode((int) row.getCell(0).getNumericCellValue())
                .code((int) row.getCell(2).getNumericCellValue())

                .name(returnStringValue(row.getCell(3)))
                .bundleCondition(returnStringValue(row.getCell(4)))
                .description(returnStringValue(row.getCell(5)))

                .lowestPrice((int) row.getCell(6).getNumericCellValue())
                .averagePrice((int) row.getCell(7).getNumericCellValue())
                .partnerCount((int) row.getCell(8).getNumericCellValue())

                .build();
    }

    private String returnStringValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();
        return switch (cellType) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.format("%.0f", cell.getNumericCellValue());
            default -> "";
        };
    }
}
