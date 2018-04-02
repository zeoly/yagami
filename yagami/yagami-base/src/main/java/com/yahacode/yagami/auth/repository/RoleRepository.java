package com.yahacode.yagami.auth.repository;

import com.yahacode.yagami.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-03-27
 */
public interface RoleRepository extends JpaRepository<Role, String> {

    List<Role> findByName(String name);

}
