package com.ojt.first_be.domain;

import com.ojt.first_be.util.excel.ExcelColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.poi.ss.usermodel.Row;

@ToString
@Getter
@NoArgsConstructor
public class StandardProduct implements Uploadable {

    @ExcelColumn(name = "대분류코드")
    private int categoryCode;
    @ExcelColumn(name = "대분류명")
    private String categoryName;
    @ExcelColumn(name = "상품코드")
    private int code;

    @ExcelColumn(name = "상품명")
    private String name;
    @ExcelColumn(name = "묶음조건")
    private String bundleCondition;
    @ExcelColumn(name = "설명")
    private String description;

    @ExcelColumn(name = "최저가")
    private int lowestPrice;
    @ExcelColumn(name = "평균가")
    private int averagePrice;
    @ExcelColumn(name = "업체수")
    private int partnerCount;

    @Builder
    public StandardProduct(int categoryCode, String categoryName,
                           int code, String name,
                           String bundleCondition, String description,
                           int lowestPrice, int averagePrice,
                           int partnerCount) {

        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.code = code;

        this.name = name;
        this.description = description;
        this.bundleCondition = bundleCondition;

        this.lowestPrice = lowestPrice;
        this.averagePrice = averagePrice;
        this.partnerCount = partnerCount;

    }

    @Override
    public void setValuesFromExcel(Row row) {

        this.categoryCode = (int) row.getCell(0).getNumericCellValue();
        this.code = (int) row.getCell(2).getNumericCellValue();
        this.name = getStringCellValue(row.getCell(3));
        this.bundleCondition = getStringCellValue(row.getCell(4));
        this.description = getStringCellValue(row.getCell(5));
        this.lowestPrice = (int) row.getCell(6).getNumericCellValue();
        this.averagePrice = (int) row.getCell(7).getNumericCellValue();
        this.partnerCount = (int) row.getCell(8).getNumericCellValue();
    }
}
