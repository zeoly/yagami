package com.yahacode.yagami.base.impl;

import com.yahacode.yagami.base.BaseModel;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.ServletContextHolder;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.consts.SessionKeyConsts;
import com.yahacode.yagami.pd.model.People;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    @Override
    public List<T> list() {
        return getBaseRepository().findAll();
    }

    @Override
    public T save(T t) {
        return getBaseRepository().save(t);
    }

    @Override
    public void delete(String id) {
        getBaseRepository().delete(id);
    }

    @Override
    public void update(T t) throws BizfwServiceException {
        if (StringUtils.isEmpty(t.getId())) {
            throw new BizfwServiceException(ErrorCode.UPDATE_ERROR);
        }
        getBaseRepository().save(t);
    }

    @Override
    public T queryById(String id) {
        return getBaseRepository().getOne(id);
    }

    @Override
    public void checkObjectNotNull(Object object, String target, String operation) throws BizfwServiceException {
        if (object == null) {
            throw new BizfwServiceException(ErrorCode.NULL_PARAM, target, operation);
        }
    }

    @Override
    public People getLoginPeople() {
        return (People) ServletContextHolder.getSession().getAttribute(SessionKeyConsts.LOGIN_PEOPLE);
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
