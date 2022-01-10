package com.yahacode.yagami.chat.scrollboard.action;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.chat.scrollboard.model.Reply;
import com.yahacode.yagami.chat.scrollboard.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zengyongli 2018-10-08
 */
@RestController
public class ReplyController extends BaseController {

    private ReplyService replyService;

    @GetMapping("/post/{postId}/reply")
    public List<Reply> getReplies(@PathVariable String postId) {
        return replyService.getAllReplies(postId);
    }

    @PostMapping("/reply")
    public void addReply(@RequestBody Reply reply) {
        replyService.save(reply);
    }

    @Autowired
    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }
}
