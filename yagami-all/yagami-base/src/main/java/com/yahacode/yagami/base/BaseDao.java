package com.yahacode.yagami.base;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * framework base DAO. provide base operations including save, update, delete, list, queryById and so on.
 *
 * @param <T>
 *         model which extends BaseModel
 */
public interface BaseDao<T extends BaseModel> {

    Class<T> getClazz();

    Session getSession();

    List<T> list();

    List<T> listAndSortAsc(String sortField);

    List<T> listAndSortDesc(String sortField);

    String save(T t);

    void saveOrUpdate(T t);

    void delete(String id);

    void update(T t);

    T queryById(String id);

    List<T> queryByFieldAndValue(String field, Object value);

    List<T> queryByFieldAndValues(String field, Object... values);

    List<T> queryByFieldLikeValue(String field, Object value);

    void deleteByFieldAndValue(String field, Object value);

    long getCountByFieldAndValue(String field, Object value);

    T load(String id);

    T get(String id);

    String getTableName();

    Query createQuery(String hql);

//	public T redisGet(String id);
//
//	public void redisSet(String id, T t);
}
