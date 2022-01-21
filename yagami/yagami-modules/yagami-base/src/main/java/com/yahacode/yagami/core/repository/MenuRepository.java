package com.yahacode.yagami.core.repository;

import com.yahacode.yagami.core.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-03-29
 */
public interface MenuRepository extends JpaRepository<Menu, String> {

    List<Menu> findByParentMenuId(String id);

    Menu findByName(String menuName);

    long countByParentMenuId(String parentMenuId);
}
