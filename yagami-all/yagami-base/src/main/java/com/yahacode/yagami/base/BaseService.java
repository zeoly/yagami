package com.yahacode.yagami.base;

import java.util.List;

/**
 * framework base Service to do CRUD and base valid function
 *
 * @param <T>
 *         model which extends BaseModel
 * @author zengyongli
 */
public interface BaseService<T extends BaseModel> {

    /**
     * get the list of all records. be careful to use this method.
     *
     * @return the list of all.
     */
    List<T> list();

    /**
     * save the record.
     *
     * @param t
     *         entity
     * @return primary key
     */
    String save(T t);

    /**
     * delete the record by primary key
     *
     * @param id
     *         primary key
     */
    void delete(String id);

    /**
     * update the entity's all fields by primary key
     *
     * @param t
     *         entity
     */
    void update(T t);

    /**
     * get the record by primary key
     *
     * @param id
     *         primary key
     * @return entity
     */
    T queryById(String id);

    /**
     * query with equal condition
     *
     * @param field
     *         field name
     * @param value
     *         target value
     * @return the list of entity that match the condition
     */
    List<T> queryByFieldAndValue(String field, Object value);

    /**
     * make sure the object is not null, otherwise throw a exception with message - "target is null，operation fail"
     *
     * @param object
     *         target object
     * @param target
     *         exception message of target
     * @param operation
     *         exception message of operation
     * @throws BizfwServiceException
     *         if the object is null
     */
    void checkObjectNotNull(Object object, String target, String operation) throws BizfwServiceException;

    /**
     * 保存并放入cache
     *
     * @param t
     *            维护对象
     * @return 主键
     */
//	public String saveWithCache(T t);

    /**
     * 先查询cache，没有则查db并放入cache
     *
     * @param id
     *            主键
     * @return 维护对象
     */
//	public T queryByIdWithCache(String id);

    /**
     * get the DAO object
     *
     * @return DAO object
     */
    BaseDao<T> getBaseDao();
}
