package com.thinkequip.anochat.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.anochat.base.BaseDao;
import com.thinkequip.anochat.base.impl.BaseServiceImpl;
import com.thinkequip.anochat.biz.ReplyDao;
import com.thinkequip.anochat.biz.ReplyService;
import com.thinkequip.anochat.core.model.Reply;

@Service
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {

	@Autowired
	private ReplyDao replyDao;

	@Override
	public BaseDao<Reply> getBaseDao() {
		return replyDao;
	}

}
