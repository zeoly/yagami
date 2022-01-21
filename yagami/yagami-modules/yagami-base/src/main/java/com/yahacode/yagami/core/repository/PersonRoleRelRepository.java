package com.yahacode.yagami.core.repository;

import com.yahacode.yagami.core.model.PersonRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * person role relation repository
 *
 * @author zengyongli
 * @since 2018-03-30
 */
public interface PersonRoleRelRepository extends JpaRepository<PersonRoleRelation, String> {

    List<PersonRoleRelation> findByPersonCode(String personCode);

    long countByRoleId(String roleId);

    void deleteByPersonCode(String personCode);
}
