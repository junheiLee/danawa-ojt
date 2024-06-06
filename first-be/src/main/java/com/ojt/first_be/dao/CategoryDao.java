package com.ojt.first_be.dao;

import com.ojt.first_be.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CategoryDao {

    int saveCategoryList(List<Category> categories);

}
