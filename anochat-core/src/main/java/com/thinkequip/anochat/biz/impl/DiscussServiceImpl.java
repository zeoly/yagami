package com.thinkequip.anochat.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thinkequip.anochat.base.BaseDao;
import com.thinkequip.anochat.base.impl.BaseServiceImpl;
import com.thinkequip.anochat.biz.DiscussDao;
import com.thinkequip.anochat.biz.DiscussService;
import com.thinkequip.anochat.core.model.Discuss;

@Service
public class DiscussServiceImpl extends BaseServiceImpl<Discuss> implements DiscussService {

	@Autowired
	private DiscussDao discussDao;

	@Override
	public BaseDao<Discuss> getBaseDao() {
		return discussDao;
	}

}
