package com.ojt.first_be.domain;

import com.ojt.first_be.util.excel.ExcelColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.poi.ss.usermodel.Row;

import java.sql.Date;

@ToString
@Getter
@NoArgsConstructor
public class PartnerProduct implements Uploadable {

    @ExcelColumn(name = "대분류코드")
    private int categoryCode;
    @ExcelColumn(name = "대분류명")
    private String categoryName;
    @ExcelColumn(name = "협력사코드")
    private String partnerCode;

    @ExcelColumn(name = "상품코드")
    private String code;
    @ExcelColumn(name = "상품명")
    private String name;

    @ExcelColumn(name = "PC가")
    private int pcPrice;
    @ExcelColumn(name = "모바일가")
    private int mobilePrice;

    @ExcelColumn(name = "등록일")
    private Date createdAt;

    @ExcelColumn(name = "url")
    private String url;
    @ExcelColumn(name = "이미지url")
    private String imageUrl;

    @Builder
    public PartnerProduct(int categoryCode, String categoryName, String partnerCode,
                          String code, String name,
                          int pcPrice, int mobilePrice,
                          Date createdAt,
                          String url, String imageUrl) {

        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.partnerCode = partnerCode;

        this.code = code;
        this.name = name;

        this.pcPrice = pcPrice;
        this.mobilePrice = mobilePrice;

        this.createdAt = createdAt;

        this.url = url;
        this.imageUrl = imageUrl;
    }

    @Override
    public void setValuesFromExcel(Row row) {
        this.categoryCode = (int) row.getCell(0).getNumericCellValue();
        this.partnerCode = getStringCellValue(row.getCell(2));

        this.code = getStringCellValue(row.getCell(3));
        this.name = getStringCellValue(row.getCell(4));

        this.pcPrice = (int) row.getCell(5).getNumericCellValue();
        this.mobilePrice = (int) row.getCell(6).getNumericCellValue();

        this.createdAt = new Date(row.getCell(7).getDateCellValue().getTime());
        this.url = getStringCellValue(row.getCell(8));
        this.imageUrl = getStringCellValue(row.getCell(9));
    }
}
