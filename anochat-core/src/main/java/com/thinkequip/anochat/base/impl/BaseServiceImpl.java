package com.thinkequip.anochat.base.impl;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.thinkequip.anochat.base.BaseService;

@Transactional
public abstract class BaseServiceImpl<T extends Serializable> implements BaseService<T> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<T> list() {
		return getBaseDao().list();
	}

	@Override
	public String save(T t) {
		return getBaseDao().save(t);
	}

	@Override
	public void delete(String id) {
		getBaseDao().delete(id);
	}

	@Override
	public void update(T t) {
		getBaseDao().update(t);
	}

	@Override
	public T queryById(String id) {
		return getBaseDao().queryById(id);
	}

	@Override
	public List<T> queryByFieldAndValue(String field, Object value) {
		return getBaseDao().queryByFieldAndValue(field, value);
	}

}
