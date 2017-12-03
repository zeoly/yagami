package com.yahacode.yagami.base.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BaseModel;

import javax.persistence.criteria.CriteriaDelete;


public class BaseDaoImpl<T extends BaseModel> implements BaseDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    // @Resource
    // private RedisTemplate<String, T> redisTemplate;

    private Class<T> clazz;

    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getClazz() {
        if (clazz == null) {
            clazz = ((Class<T>) (((ParameterizedType) (this.getClass().getGenericSuperclass()))
                    .getActualTypeArguments()[0]));
        }
        return clazz;
    }

    @Override
    public String save(T t) {
        return (String) getSession().save(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
    }

    @Override
    public List<T> list() {
        Criteria criteria = getSession().createCriteria(getClazz());
        return criteria.list();
    }

    @Override
    public List<T> listAndSortAsc(String sortField) {
        Criteria criteria = getSession().createCriteria(getClazz());
        criteria.addOrder(Order.asc(sortField));
        return criteria.list();
    }

    @Override
    public List<T> listAndSortDesc(String sortField) {
        Criteria criteria = getSession().createCriteria(getClazz());
        criteria.addOrder(Order.desc(sortField));
        return criteria.list();
    }

    @Override
    public void delete(String id) {
        getSession().delete(load(id));
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public T queryById(String id) {
        return this.get(id);
    }

    @Override
    public List<T> queryByFieldAndValue(String field, Object value) {
        Criteria criteria = getSession().createCriteria(getClazz());
        return criteria.add(Restrictions.eq(field, value)).list();
    }

    @Override
    public List<T> queryByFieldAndValues(String field, Object... values) {
        Criteria criteria = getSession().createCriteria(getClazz());
        return criteria.add(Restrictions.in(field, values)).list();
    }

    @Override
    public void deleteByFieldAndValue(String field, Object value) {
        String hql = "delete from " + getTableName() + " as t where t." + field + " = :value";
        Query query = createQuery(hql);
        query.setParameter("value", value);
        query.executeUpdate();
    }

    @Override
    public long getCountByFieldAndValue(String field, Object value) {
        String hql = "select count(0) from " + getTableName() + " as t where t." + field + " = :value";
        Query query = getSession().createQuery(hql);
        query.setParameter("value", value);
        return (long) query.uniqueResult();
    }

    @Override
    public T load(String id) {
        return getSession().load(getClazz(), id);
    }

    @Override
    public T get(String id) {
        return getSession().get(getClazz(), id);
    }

    @Override
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Query createQuery(String hql) {
        Session session = getSession();
        return session.createQuery(hql);
    }

    @Override
    public String getTableName() {
        return getClazz().getSimpleName();
    }

    // @Override
    // public T redisGet(String id) {
    // return redisTemplate.opsForValue().get(id);
    // }
    //
    // @Override
    // public void redisSet(String id, T t) {
    // redisTemplate.opsForValue().set(id, t);
    // }

}
