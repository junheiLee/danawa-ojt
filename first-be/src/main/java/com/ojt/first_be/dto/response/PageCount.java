package com.ojt.first_be.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PageCount {

    private int totalPage;
    private int totalItem;

    @Builder
    public PageCount(int totalPage, int totalItem) {
        this.totalPage = totalPage;
        this.totalItem = totalItem;
    }
}
