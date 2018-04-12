package com.yahacode.yagami.pd.repository;

import com.yahacode.yagami.base.BaseRepository;
import com.yahacode.yagami.pd.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-03-29
 */
public interface DepartmentRepository extends JpaRepository<Department, String> {

    Department findByCode(String code);

    List<Department> findByParentDepartmentId(String departmentId);

    long countByParentDepartmentId(String departmentId);
}
