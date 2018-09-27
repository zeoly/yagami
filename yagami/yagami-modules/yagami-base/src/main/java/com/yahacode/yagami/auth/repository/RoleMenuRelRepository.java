package com.yahacode.yagami.auth.repository;

import com.yahacode.yagami.auth.model.RoleMenuRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-09-26
 */
public interface RoleMenuRelRepository extends JpaRepository<RoleMenuRelation, String> {

    List<RoleMenuRelation> findByRoleId(String roleId);

    void deleteByMenuId(String menuId);

    void deleteByRoleId(String roleId);
}
