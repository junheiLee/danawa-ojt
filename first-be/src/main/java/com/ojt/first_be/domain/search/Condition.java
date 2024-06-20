package com.ojt.first_be.domain.search;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static com.ojt.first_be.constant.Common.OUTPUT_LIST_LIMIT_SIZE;

@Slf4j
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Condition {

    private int page;
    private int limit;
    private int offset;

    private String searchName;
    private String searchCode;

    private Integer category;
    private String orderBy;

    public Condition setPageInfo(int limit, int offset) {

        this.limit = limit;
        this.offset = offset;

        return this;
    }

    public void setPage(int page) {

        this.page = page;
        this.limit = OUTPUT_LIST_LIMIT_SIZE;
        this.offset = (Math.abs(page) - 1) * OUTPUT_LIST_LIMIT_SIZE;
    }

}
