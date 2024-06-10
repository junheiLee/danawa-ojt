package com.ojt.first_be.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Partner implements Uploadable {

    private String code;
    private String name;

    @Builder
    public Partner(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
