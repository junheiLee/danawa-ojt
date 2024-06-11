package com.ojt.first_be.dto.response;

import com.ojt.first_be.domain.PartnerProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PartnerProductList extends BaseResponse {

    private Integer totalPage;
    private List<PartnerProduct> products;

    @Builder
    public PartnerProductList(String resultCode, String message,
                              Integer totalPage,
                              List<PartnerProduct> products) {
        super(resultCode, message);
        this.totalPage = totalPage;
        this.products = products;
    }
}
