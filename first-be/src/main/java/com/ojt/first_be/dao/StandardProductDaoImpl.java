package com.ojt.first_be.dao;

import com.ojt.first_be.dao.mapper.StandardProductMapper;
import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.domain.search.Condition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class StandardProductDaoImpl implements StandardProductDao {

    private final StandardProductMapper standardProductMapper;

    @Override
    public int saveAll(List<StandardProduct> products) {

        return standardProductMapper.insertAll(products);
    }

    @Override
    public int save(StandardProduct product) {

        return standardProductMapper.insert(product);
    }

    @Override
    public List<StandardProduct> findAll(Condition condition) {
        log.info(condition.toString());
        return standardProductMapper.selectAll(condition);
    }

    @Override
    public int countAll(Condition condition) {
        return standardProductMapper.countAll(condition);
    }
}
