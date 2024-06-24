package com.ojt.first_be.dao;

import com.ojt.first_be.domain.PartnerProduct;
import com.ojt.first_be.dto.request.Condition;

import java.util.List;

public interface PartnerProductDao {

    int saveAll(List<PartnerProduct> partnerProducts);

    List<PartnerProduct> findAll(Condition condition);

    int countAll(Condition condition);
}
