package com.ojt.first_be.dao;

import com.ojt.first_be.domain.Category;

import java.util.List;

public interface CategoryDao {

    int saveCategoryList(List<Category> categories);

}
