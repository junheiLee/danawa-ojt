package com.ojt.first_be.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
public class PartnerProduct implements Uploadable {

    private int categoryCode;
    private String categoryName;
    private String partnerCode;

    private String code;
    private String name;

    private int pcPrice;
    private int mobilePrice;

    private Date createdAt;

    private String url;
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
}
