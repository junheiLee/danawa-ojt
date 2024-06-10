package com.ojt.first_be.dao;

import com.ojt.first_be.dao.mapper.StandardProductMapper;
import com.ojt.first_be.domain.StandardProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class StandardProductDaoImpl implements StandardProductDao {

    private final StandardProductMapper standardProductMapper;

    @Override
    public int saveAll(List<StandardProduct> products) {


        try {
            return standardProductMapper.insertAll(products);
        } catch (DataAccessException e) {
            throw new RuntimeException("임시" + "배치 처리 실패");
        }
    }

    @Override
    public int save(StandardProduct product) {

        try {
            return standardProductMapper.insert(product);
        } catch (DataAccessException e) {
            throw new RuntimeException("임시" + "단일 처리 실패");
        }
    }
}
