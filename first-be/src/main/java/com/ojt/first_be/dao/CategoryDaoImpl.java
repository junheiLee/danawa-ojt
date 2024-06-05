package com.ojt.first_be.dao;

import com.ojt.first_be.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public int saveCategoryList(List<Category> categories) {
        return 0;
    }
}
