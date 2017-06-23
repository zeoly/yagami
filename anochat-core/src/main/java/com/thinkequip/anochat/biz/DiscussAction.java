package com.thinkequip.anochat.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkequip.anochat.core.model.Discuss;
import com.thinkequip.anochat.core.model.Reply;

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
