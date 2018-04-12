package com.yahacode.yagami.base;

import com.yahacode.yagami.pd.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

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
     * @throws if
     *         entity's pk is null
     */
    void update(T t) throws BizfwServiceException;

    /**
     * get the record by primary key
     *
     * @param id
     *         primary key
     * @return entity
     */
    T queryById(String id);

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
     * get the login people
     *
     * @return login people
     * @throws BizfwServiceException
     *         framework exception
     */
    People getLoginPeople() throws BizfwServiceException;

    /**
     * 保存并放入cache
     *
     * @param t
     *         维护对象
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
     * get the BaseRepository object
     *
     * @return BaseRepository
     */
    JpaRepository<T, String> getBaseRepository();
}
