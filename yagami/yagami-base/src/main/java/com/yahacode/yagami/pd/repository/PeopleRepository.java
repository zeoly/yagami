package com.yahacode.yagami.pd.repository;

import com.yahacode.yagami.base.BaseRepository;
import com.yahacode.yagami.pd.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-03-27
 */
public interface PeopleRepository extends JpaRepository<People, String> {

    People findByCode(String code);

    List<People> findByDepartmentId(String departmentId);

    long countByDepartmentId(String departmentId);
}
