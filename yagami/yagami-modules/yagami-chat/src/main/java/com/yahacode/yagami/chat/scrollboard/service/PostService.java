package com.yahacode.yagami.chat.scrollboard.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.chat.scrollboard.model.Post;

import java.util.List;

/**
 * @author zengyongli 2018-09-16
 */
public interface PostService extends BaseService<Post> {

    List<Post> findAllPosts();
}
