package com.ojt.first_be.dao;

import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.dto.request.Condition;

import java.util.List;

public interface StandardProductDao {

    int saveAll(List<StandardProduct> products);

    List<StandardProduct> findAll(Condition condition);

    int countAll(Condition condition);
}
