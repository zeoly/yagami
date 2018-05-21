package com.yahacode.yagami.chat.scrollboard.repository;

import com.yahacode.yagami.chat.scrollboard.model.Scrollboard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zengyongli 2018-05-07
 */
public interface ScrollboardRepository extends JpaRepository<Scrollboard, String> {
}
