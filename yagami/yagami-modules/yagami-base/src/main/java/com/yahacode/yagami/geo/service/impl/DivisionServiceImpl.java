package com.yahacode.yagami.geo.service.impl;

import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.geo.model.Division;
import com.yahacode.yagami.geo.repository.DivisionRepository;
import com.yahacode.yagami.geo.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zengyongli 2018-05-18
 */
@Service
public class DivisionServiceImpl extends BaseServiceImpl<Division> implements DivisionService {

    private DivisionRepository divisionRepository;

    @Override
    public Division getParent(String code) {
        return null;
    }

    @Override
    public List<Division> getChidren(String code) {
        return null;
    }

    @Autowired
    public void setDivisionRepository(DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
    }

    @Override
    public JpaRepository<Division, String> getBaseRepository() {
        return divisionRepository;
    }

}
