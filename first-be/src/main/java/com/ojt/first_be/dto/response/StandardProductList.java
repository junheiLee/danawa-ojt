package com.ojt.first_be.dto.response;

import com.ojt.first_be.domain.StandardProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class StandardProductList extends BaseResponse {

    private List<StandardProduct> products;

    @Builder
    public StandardProductList(String resultCode, String message,
                               List<StandardProduct> products) {
        super(resultCode, message);
        this.products = products;
    }
}
