package com.ojt.first_be.dao;

import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.domain.search.Condition;

import java.util.List;

public interface StandardProductDao {

    int saveAll(List<StandardProduct> products);

    int save(StandardProduct product);

    List<StandardProduct> findAll(Condition condition);

    int countAll(Condition condition);
}
