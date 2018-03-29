package com.yahacode.yagami.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author zengyongli 2018-03-29
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseModel, String> extends JpaRepository {
}
