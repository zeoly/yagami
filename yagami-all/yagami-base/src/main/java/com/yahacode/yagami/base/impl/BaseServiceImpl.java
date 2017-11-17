package com.yahacode.yagami.base.impl;

import java.util.List;

import com.yahacode.yagami.base.common.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.yahacode.yagami.base.BaseModel;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.consts.ErrorCode;

@Transactional
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

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

    @Override
    public T queryUniqueByFieldAndValue(String field, Object value) {
        List<T> list = queryByFieldAndValue(field, value);
        if (ListUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public void checkObjectNotNull(Object object, String target, String operation) throws BizfwServiceException {
        if (object == null) {
            throw new BizfwServiceException(ErrorCode.NULL_PARAM, target, operation);
        }
    }

//	@Override
//	public String saveWithCache(T t) {
//		String id = save(t);
//		try {
//			getBaseDao().redisSet(id, t);
//		} catch (Exception e) {
//			logger.error("error save[{}][{}] in cache", getBaseDao().getClazz().getName(), id);
//		}
//		return id;
//	}
//
//	@Override
//	public T queryByIdWithCache(String id) {
//		T t = null;
//		try {
//			t = getBaseDao().redisGet(id);
//		} catch (Exception e) {
//			logger.error("error load[{}][{}] in cache", getBaseDao().getClazz().getName(), id);
//		}
//		if (t != null) {
//			logger.info("load[{}][{}] in cache", getBaseDao().getClazz().getName(), id);
//			return t;
//		}
//		logger.info("load[{}][{}] in cache fail", getBaseDao().getClazz().getName(), id);
//		t = queryById(id);
//		if (t != null) {
//			try {
//				getBaseDao().redisSet(id, t);
//			} catch (Exception e) {
//				logger.error("error save[{}][{}] in cache", getBaseDao().getClazz().getName(), id);
//			}
//		}
//		return t;
//	}
}
