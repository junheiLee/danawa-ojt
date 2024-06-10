package com.ojt.first_be.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StandardProduct implements Uploadable {

    private int code;
    private int categoryCode;

    private String name;
    private String bundleCondition;
    private String description;

    private int lowestPrice;
    private int averagePrice;
    private int partnerCount;

    @Builder
    public StandardProduct(int code, int categoryCode,
                           String name, String bundleCondition,
                           String description,
                           int lowestPrice, int averagePrice,
                           int partnerCount) {
        this.code = code;
        this.categoryCode = categoryCode;

        this.name = name;
        this.description = description;
        this.bundleCondition = bundleCondition;

        this.lowestPrice = lowestPrice;
        this.averagePrice = averagePrice;
        this.partnerCount = partnerCount;

    }
}
