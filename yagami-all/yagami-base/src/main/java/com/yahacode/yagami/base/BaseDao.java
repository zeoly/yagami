package com.yahacode.yagami.base;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public interface BaseDao<T extends BaseModel> {

	public Class<T> getClazz();

	public Session getSession();

	public List<T> list();

	public List<T> listAndSortAsc(String sortField);

	public List<T> listAndSortDesc(String sortField);

	public String save(T t);

	public void saveOrUpdate(T t);

	public void delete(String id);

	public void update(T t);

	public T queryById(String id);

	public List<T> queryByFieldAndValue(String field, Object value);

	public void deleteByFieldAndValue(String field, Object value);

	public long getCountByFieldAndValue(String field, Object value);

	public T load(String id);

	public T get(String id);

	public String getTableName();

	public Query createQuery(String hql);

//	public T redisGet(String id);
//
//	public void redisSet(String id, T t);
}
