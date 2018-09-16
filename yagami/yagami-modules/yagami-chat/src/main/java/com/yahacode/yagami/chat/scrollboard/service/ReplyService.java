package com.yahacode.yagami.chat.scrollboard.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.chat.scrollboard.model.Reply;

import java.util.List;

/**
 * @author zengyongli 2018-09-16
 */
public interface ReplyService extends BaseService<Reply> {

    List<Reply> getAllReplies(String postId);
}
