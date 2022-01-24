package com.yahacode.yagami.base.impl;

import com.yahacode.yagami.base.BaseModel;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.mvc.SessionService;
import com.yahacode.yagami.core.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {

    @Autowired
    private SessionService sessionService;

    @Override
    public List<T> findAll() {
        return getBaseRepository().findAll();
    }

    @Override
    public String initAndSave(T t) {
        t.init(getLoginPerson().getCode());
        return getBaseRepository().save(t).getId();
    }

    @Override
    public void deleteById(String id) {
        getBaseRepository().deleteById(id);
    }

    @Override
    public void updateById(T t) throws ServiceException {
        if (StringUtils.isEmpty(t.getId())) {
            throw new ServiceException(ErrorCode.UPDATE_MISS_PK);
        }
        t.update(getLoginPerson().getCode());
        getBaseRepository().save(t);
    }

    @Override
    public T findById(String id) {
        return getBaseRepository().findById(id).get();
    }

    @Override
    public void checkObjectNotNull(Object object, String target, String operation) throws ServiceException {
        if (object == null) {
            throw new ServiceException(ErrorCode.NULL_PARAM, target, operation);
        }
    }

    @Override
    public Person getLoginPerson() {
        return sessionService.getLoginPerson();
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
