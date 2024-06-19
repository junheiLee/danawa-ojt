package com.ojt.first_be.dto.response;

import com.ojt.first_be.domain.PartnerProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class PartnerProductList extends BaseResponse {

    private int totalItemsCount;
    private List<PartnerProduct> products;

    @Builder
    public PartnerProductList(String resultCode, String message,
                              int totalItemsCount,
                              List<PartnerProduct> products) {
        super(resultCode, message);
        this.totalItemsCount = totalItemsCount;
        this.products = products;
    }
}
