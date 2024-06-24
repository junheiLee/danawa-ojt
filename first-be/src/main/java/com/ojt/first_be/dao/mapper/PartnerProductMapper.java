package com.ojt.first_be.dao.mapper;

import com.ojt.first_be.domain.PartnerProduct;
import com.ojt.first_be.dto.request.Condition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PartnerProductMapper {

    int insertAll(List<PartnerProduct> partnerProducts);

    int insert(PartnerProduct partnerProduct);

    List<PartnerProduct> selectAll(Condition condition);

    int countAll(Condition condition);
}
