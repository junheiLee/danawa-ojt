package com.ojt.first_be.dto.response;

import com.ojt.first_be.domain.StandardProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
public class StandardProductList extends BaseResponse {

    private int totalItemsCount;
    private List<StandardProduct> products;

    @Builder
    public StandardProductList(String resultCode, String message,
                               int totalItemsCount,
                               List<StandardProduct> products) {
        super(resultCode, message);
        this.totalItemsCount = totalItemsCount;
        this.products = products;
    }
}
