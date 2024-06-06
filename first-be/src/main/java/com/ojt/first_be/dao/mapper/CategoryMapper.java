package com.ojt.first_be.dao.mapper;

import com.ojt.first_be.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int insertCategories(List<Category> categories);

    int insertCategory(Category category);
}
