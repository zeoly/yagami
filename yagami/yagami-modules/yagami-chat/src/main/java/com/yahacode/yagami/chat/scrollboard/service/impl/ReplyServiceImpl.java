package com.yahacode.yagami.chat.scrollboard.service.impl;

import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.chat.scrollboard.model.Reply;
import com.yahacode.yagami.chat.scrollboard.repository.ReplyRepository;
import com.yahacode.yagami.chat.scrollboard.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zengyongli 2018-09-16
 */
@Service
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Override
    public List<Reply> getAllReplies(String postId) {
        return replyRepository.findAllByPostIdByOrderByUpdateDateDesc(postId);
    }

    @Override
    public JpaRepository<Reply, String> getBaseRepository() {
        return this.replyRepository;
    }

}
