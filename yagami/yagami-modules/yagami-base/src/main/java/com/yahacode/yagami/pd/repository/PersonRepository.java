package com.yahacode.yagami.pd.repository;

import com.yahacode.yagami.pd.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * person repository
 *
 * @author zengyongli
 * @since 2018-03-27
 */
public interface PersonRepository extends JpaRepository<Person, String> {

    Person findByCode(String code);
}
