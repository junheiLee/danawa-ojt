package com.ojt.first_be.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StandardProduct implements Uploadable {

    private int categoryCode;
    private String categoryName;
    private int code;

    private String name;
    private String bundleCondition;
    private String description;

    private int lowestPrice;
    private int averagePrice;
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
}
