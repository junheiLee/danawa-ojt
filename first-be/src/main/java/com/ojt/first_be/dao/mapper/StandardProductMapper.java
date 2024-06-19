package com.ojt.first_be.dao.mapper;

import com.ojt.first_be.domain.StandardProduct;
import com.ojt.first_be.domain.search.Condition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StandardProductMapper {

    int insertAll(List<StandardProduct> products);

    int insert(StandardProduct product);

    List<StandardProduct> selectAll(Condition condition);

    int countAll(Condition condition);
}
