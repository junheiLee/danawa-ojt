package com.ojt.first_be.dao;

import com.ojt.first_be.dao.mapper.CategoryMapper;
import com.ojt.first_be.domain.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final CategoryMapper categoryMapper;

    @Override
    public int saveAll(List<Category> categories) {

        return categoryMapper.insertAll(categories);
    }

    @Override
    public int save(Category category) {

        return categoryMapper.insert(category);
    }
}
