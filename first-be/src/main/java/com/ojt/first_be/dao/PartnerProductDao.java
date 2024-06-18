package com.ojt.first_be.dao;

import com.ojt.first_be.domain.PartnerProduct;

import java.util.List;

public interface PartnerProductDao {

    int saveAll(List<PartnerProduct> partnerProducts);

    int save(PartnerProduct partnerProduct);

    List<PartnerProduct> findAll(int limit, int offset);

    int countAll();

}
