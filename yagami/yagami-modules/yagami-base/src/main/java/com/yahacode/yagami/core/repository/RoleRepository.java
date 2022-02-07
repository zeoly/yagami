package com.yahacode.yagami.core.repository;

import com.yahacode.yagami.core.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * role repository
 *
 * @author zengyongli
 * @since 2018-03-27
 */
public interface RoleRepository extends JpaRepository<Role, String> {

    List<Role> findAllOrderByName();

    Role findByName(String name);

}
