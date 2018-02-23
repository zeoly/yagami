package com.yahacode.yagami.pd.service;

import java.util.List;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.People;

/**
 * people service
 *
 * @author zengyongli
 */
public interface PeopleService extends BaseService<People> {

    /**
     * add a new people
     *
     * @param people
     *         entity
     * @return pk
     * @throws BizfwServiceException
     *         if the same name people is exists;
     *         if the people's department is not exists;
     */
    String addPeople(People people) throws BizfwServiceException;

    /**
     * modify people's roles
     *
     * @param people
     *         entity
     * @throws BizfwServiceException
     *         framework exception
     */
    void modifyPeople(People people) throws BizfwServiceException;

    /**
     * delete people by pk
     *
     * @param peopleId
     *         pk
     * @throws BizfwServiceException
     *         if the target people is the operator
     */
    void deletePeople(String peopleId) throws BizfwServiceException;

    /**
     * get people by code
     *
     * @param code
     *         people code
     * @return entity
     * @throws BizfwServiceException
     *         framework exception
     */
    People getByCode(String code) throws BizfwServiceException;

    /**
     * count how many people in a department
     *
     * @param department
     *         target department
     * @return the people count
     * @throws BizfwServiceException
     *         framework exception
     */
    long getPeopleCountByDepartment(Department department) throws BizfwServiceException;

    /**
     * get the people list in a department
     *
     * @param departmentId
     *         target department pk
     * @return the list of people
     * @throws BizfwServiceException
     *         framework exception
     */
    List<People> getPeopleListByDepartment(String departmentId) throws BizfwServiceException;

    /**
     * unlock a people's account
     *
     * @param people
     *         entity
     * @throws BizfwServiceException
     *         if the status of people is not locked
     */
    void unlock(People people) throws BizfwServiceException;

    /**
     * modify people's login password
     *
     * @param people
     *         target people
     * @param oldPwd
     *         old password
     * @param newPwd
     *         new password
     * @throws BizfwServiceException
     *         if the old password is not correct
     */
    void modifyPassword(People people, String oldPwd, String newPwd) throws BizfwServiceException;
}
