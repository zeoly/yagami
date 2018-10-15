package com.yahacode.yagami.chat.scrollboard.repository;

import com.yahacode.yagami.base.BaseRepository;
import com.yahacode.yagami.chat.scrollboard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zengyongli 2018-09-16
 */
public interface PostRepository extends BaseRepository<Post, String> {

    List<Post> findAllByOrderByUpdateDateDesc();
}
