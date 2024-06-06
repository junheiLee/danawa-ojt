package com.ojt.first_be.dao;

import com.ojt.first_be.dao.mapper.CategoryMapper;
import com.ojt.first_be.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final CategoryMapper categoryMapper;

    @Override
    public int saveCategoryList(List<Category> categories) {

        return categoryMapper.save(categories);
    }
}
