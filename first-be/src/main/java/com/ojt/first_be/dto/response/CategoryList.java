package com.ojt.first_be.dto.response;

import com.ojt.first_be.constant.ResultCode;
import com.ojt.first_be.domain.Category;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoryList extends BaseResponse {

    private List<Category> categories;

    @Builder
    public CategoryList(ResultCode resultCode, List<Category> categories) {
        super(resultCode);
        this.categories = categories;
    }
}
