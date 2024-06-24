package com.ojt.first_be.dao;

import com.ojt.first_be.dao.mapper.PartnerProductMapper;
import com.ojt.first_be.domain.PartnerProduct;
import com.ojt.first_be.dto.request.Condition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class PartnerProductDaoImpl implements PartnerProductDao {

    private final PartnerProductMapper partnerProductMapper;

    @Override
    public int saveAll(List<PartnerProduct> partnerProducts) {

        return partnerProductMapper.insertAll(partnerProducts);
    }

    @Override
    public List<PartnerProduct> findAll(Condition condition) {

        return partnerProductMapper.selectAll(condition);
    }

    @Override
    public int countAll(Condition condition) {
        return partnerProductMapper.countAll(condition);
    }

}
