package com.yahacode.yagami.core.repository;

import com.yahacode.yagami.core.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * department repository
 *
 * @author zengyongli
 * @since 2018-03-29
 */
public interface DepartmentRepository extends JpaRepository<Department, String> {

    Department findByCode(String code);

    List<Department> findByParentCode(String parentCode);

    long countByParentCode(String parentCode);
}
