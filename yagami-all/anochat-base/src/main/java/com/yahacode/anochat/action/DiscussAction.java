package com.yahacode.anochat.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.anochat.model.Discuss;
import com.yahacode.anochat.model.Reply;
import com.yahacode.anochat.service.DiscussService;
import com.yahacode.anochat.service.ReplyService;

@Controller
public class DiscussAction {

	@Autowired
	private DiscussService discussService;

	@Autowired
	private ReplyService replyService;

	@RequestMapping("/list")
	@ResponseBody
	public List<Discuss> list() {
		return discussService.list();
	}

	@RequestMapping("/postDiscuss")
	@ResponseBody
	public void postDiscuss(Discuss discuss) {
		discussService.save(discuss);
	}

	@RequestMapping("/getReplyList")
	@ResponseBody
	public List<Reply> getReplyList(String discussId) {
		return replyService.queryByFieldAndValue(Reply.COLUMN_DISCUSS_ID, discussId);
	}

	@RequestMapping("/postReply")
	@ResponseBody
	public void postReply(Reply reply) {
		replyService.save(reply);
	}
}
