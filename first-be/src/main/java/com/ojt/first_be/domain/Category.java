package com.ojt.first_be.domain;

import lombok.Builder;

public class Category {

    private final int categoryCode;
    private final String categoryName;

    @Builder
    public Category(int categoryCode, String categoryName) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
    }
}
