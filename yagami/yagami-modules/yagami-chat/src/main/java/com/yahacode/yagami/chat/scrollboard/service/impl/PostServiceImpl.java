package com.yahacode.yagami.chat.scrollboard.service.impl;

import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.chat.scrollboard.model.Post;
import com.yahacode.yagami.chat.scrollboard.repository.PostRepository;
import com.yahacode.yagami.chat.scrollboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zengyongli 2018-09-16
 */
@Service
public class PostServiceImpl extends BaseServiceImpl<Post> implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> findAllPosts() {
        return postRepository.findAllByOrderByUpdateDateDesc();
    }

    @Override
    public JpaRepository<Post, String> getBaseRepository() {
        return postRepository;
    }

}
