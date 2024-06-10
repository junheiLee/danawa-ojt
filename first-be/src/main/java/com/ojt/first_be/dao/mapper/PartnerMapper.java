package com.ojt.first_be.dao.mapper;

import com.ojt.first_be.domain.Partner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PartnerMapper {

    int insertAll(List<Partner> partners);

    int insert(Partner partner);
}
