package com.ojt.first_be.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Date;

@Getter
@ToString
public class PartnerProduct implements Uploadable {

    private String code;
    private String partnerCode;
    private int categoryCode;

    private String name;
    private int pcPrice;
    private int mobilePrice;

    private String url;
    private String imageUrl;

    private Date createdAt;

    @Builder
    public PartnerProduct(String code, String partnerCode, int categoryCode,
                          String name, int pcPrice, int mobilePrice,
                          String url, String imageUrl,
                          Date createdAt) {
        this.code = code;
        this.partnerCode = partnerCode;
        this.categoryCode = categoryCode;

        this.name = name;
        this.pcPrice = pcPrice;
        this.mobilePrice = mobilePrice;

        this.url = url;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;

    }
}
