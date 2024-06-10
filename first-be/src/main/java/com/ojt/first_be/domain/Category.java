package com.ojt.first_be.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Category implements Uploadable {

    private int code;
    private String name;

    @Builder
    public Category(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
