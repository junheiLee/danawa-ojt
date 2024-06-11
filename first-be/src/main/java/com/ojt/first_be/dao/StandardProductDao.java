package com.ojt.first_be.dao;

import com.ojt.first_be.domain.StandardProduct;

import java.util.List;

public interface StandardProductDao {

    int saveAll(List<StandardProduct> products);

    int save(StandardProduct product);

    List<StandardProduct> findAll(int limit, int offset);

    int countAll();
}
