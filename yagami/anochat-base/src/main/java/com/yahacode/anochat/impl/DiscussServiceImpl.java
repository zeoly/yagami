package com.yahacode.anochat.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yahacode.anochat.dao.DiscussDao;
import com.yahacode.anochat.model.Discuss;
import com.yahacode.anochat.service.DiscussService;
import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.impl.BaseServiceImpl;

@Service
public class DiscussServiceImpl extends BaseServiceImpl<Discuss> implements DiscussService {

	@Autowired
	private DiscussDao discussDao;

	@Override
	public BaseDao<Discuss> getBaseDao() {
		return discussDao;
	}

}
