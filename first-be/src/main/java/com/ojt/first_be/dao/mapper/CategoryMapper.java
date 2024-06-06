package com.ojt.first_be.dao.mapper;

import com.ojt.first_be.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int save(List<Category> categories);
}
