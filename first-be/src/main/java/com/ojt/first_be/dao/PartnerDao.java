package com.ojt.first_be.dao;

import com.ojt.first_be.domain.Partner;

import java.util.List;

public interface PartnerDao {

    int saveAll(List<Partner> partners);

}
