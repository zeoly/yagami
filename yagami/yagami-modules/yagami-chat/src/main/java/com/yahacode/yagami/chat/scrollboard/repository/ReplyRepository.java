package com.yahacode.yagami.chat.scrollboard.repository;

import com.yahacode.yagami.chat.scrollboard.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-09-16
 */
public interface ReplyRepository extends JpaRepository<Reply, String> {

    List<Reply> findAllByPostIdByOrderByUpdateDateDesc(String postId);
}
