package com.yahacode.anochat.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yahacode.anochat.dao.ReplyDao;
import com.yahacode.anochat.model.Reply;
import com.yahacode.anochat.service.ReplyService;
import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.impl.BaseServiceImpl;

@Service
public class ReplyServiceImpl extends BaseServiceImpl<Reply> implements ReplyService {

	@Autowired
	private ReplyDao replyDao;

	@Override
	public BaseDao<Reply> getBaseDao() {
		return replyDao;
	}

}
