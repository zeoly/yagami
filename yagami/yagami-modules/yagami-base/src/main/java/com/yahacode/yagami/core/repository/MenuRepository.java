package com.yahacode.yagami.core.repository;

import com.yahacode.yagami.core.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * menu repository
 *
 * @author zengyongli
 * @since 2018-03-29
 */
public interface MenuRepository extends JpaRepository<Menu, String> {

    List<Menu> findByParentIdOrderByOrders(String id);

    Menu findByName(String name);

    long countByParentId(String parentId);
}
