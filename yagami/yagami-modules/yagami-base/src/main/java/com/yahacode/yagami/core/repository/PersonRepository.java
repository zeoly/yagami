package com.yahacode.yagami.core.repository;

import com.yahacode.yagami.core.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * person repository
 *
 * @author zengyongli
 * @since 2018-03-27
 */
public interface PersonRepository extends JpaRepository<Person, String> {

    Person findByCode(String code);

    List<Person> findByDepartmentCode(String departmentCode);

    long countByDepartmentCode(String departmentCode);
}
