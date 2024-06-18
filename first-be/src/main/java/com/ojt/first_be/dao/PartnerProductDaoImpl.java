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

        try {
            return partnerProductMapper.insertAll(partnerProducts);
        } catch (DataAccessException e) {
            throw new RuntimeException("임시" + "배치 처리 실패");
        }
    }

    @Override
    public int save(PartnerProduct partnerProduct) {

        try {
            return partnerProductMapper.insert(partnerProduct);

        } catch (DataAccessException e) {
            throw new RuntimeException("임시" + "단일 실패");
        }
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
