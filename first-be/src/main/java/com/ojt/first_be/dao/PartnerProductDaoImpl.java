package com.ojt.first_be.dao;

import com.ojt.first_be.dao.mapper.PartnerProductMapper;
import com.ojt.first_be.domain.PartnerProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PartnerProductDaoImpl implements PartnerProductDao {

    private final PartnerProductMapper partnerProductMapper;

    @Override
    public int saveAll(List<PartnerProduct> partnerProducts) {

        return partnerProductMapper.insertAll(partnerProducts);
    }

    @Override
    public int save(PartnerProduct partnerProduct) {

        return partnerProductMapper.insert(partnerProduct);
    }

    @Override
    public List<PartnerProduct> findAll(int limit, int offset) {

        return partnerProductMapper.selectAll(limit, offset);
    }

    @Override
    public int countAll() {

        return partnerProductMapper.countAll();
    }
}
