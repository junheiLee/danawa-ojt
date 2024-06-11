package com.ojt.first_be.dao.mapper;

import com.ojt.first_be.domain.StandardProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StandardProductMapper {

    int insertAll(List<StandardProduct> products);

    int insert(StandardProduct product);

    List<StandardProduct> selectAll(int limit, int offset);

    int countAll();
}
