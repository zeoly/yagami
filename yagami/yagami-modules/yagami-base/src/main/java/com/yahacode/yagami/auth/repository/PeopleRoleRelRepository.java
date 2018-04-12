package com.yahacode.yagami.auth.repository;

import com.yahacode.yagami.auth.model.PeopleRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-03-30
 */
public interface PeopleRoleRelRepository extends JpaRepository<PeopleRoleRelation, String> {

    List<PeopleRoleRelation> findByPeopleId(String peopleId);

    long countByRoleId(String roleId);

    void deleteByPeopleId(String peopleId);
}
