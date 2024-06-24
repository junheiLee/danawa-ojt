package com.ojt.first_be.dao;

import com.ojt.first_be.dao.mapper.PartnerMapper;
import com.ojt.first_be.domain.Partner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PartnerDaoImpl implements PartnerDao {

    private final PartnerMapper partnerMapper;

    @Override
    public int saveAll(List<Partner> partners) {

        return partnerMapper.insertAll(partners);
    }

}
