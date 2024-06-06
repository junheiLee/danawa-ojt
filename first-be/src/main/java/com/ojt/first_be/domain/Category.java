package com.ojt.first_be.domain;

import lombok.Builder;
import lombok.ToString;

@ToString
public class Category {

    private final int code;
    private final String name;

    @Builder
    public Category(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
