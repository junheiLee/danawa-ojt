package com.ojt.first_be.dao.mapper;

import com.ojt.first_be.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int insertAll(List<Category> categories);

    int insert(Category category);

    List<Category> selectAll();
}
