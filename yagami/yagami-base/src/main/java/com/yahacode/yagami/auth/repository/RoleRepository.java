package com.yahacode.yagami.auth.repository;

import com.yahacode.yagami.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zengyongli 2018-03-27
 */
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByName(String name);

}
