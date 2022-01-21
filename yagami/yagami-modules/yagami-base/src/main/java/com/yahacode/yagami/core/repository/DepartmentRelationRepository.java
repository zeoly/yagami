package com.yahacode.yagami.core.repository;

import com.yahacode.yagami.core.model.DepartmentRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-04-01
 */
public interface DepartmentRelationRepository extends JpaRepository<DepartmentRelation, String> {

    void deleteByChildDepartmentId(String departmentId);

    List<DepartmentRelation> findByChildDepartmentId(String departmentId);

    DepartmentRelation findByChildDepartmentIdAndParentLevel(String childDepartmentId, int parentLevel);
}
