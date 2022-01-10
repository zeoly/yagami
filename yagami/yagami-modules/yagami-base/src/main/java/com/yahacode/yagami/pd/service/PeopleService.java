package com.yahacode.yagami.pd.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.Person;

import java.util.List;

/**
 * people service
 *
 * @author zengyongli
 */
public interface PeopleService extends BaseService<Person> {

    /**
     * add a new people
     *
     * @param people
     *         entity
     * @return pk
     * @throws ServiceException
     *         if the same name people is exists;
     *         if the people's department is not exists;
     */
    String addPeople(Person people) throws ServiceException;

    /**
     * modify people's roles
     *
     * @param people
     *         entity
     * @throws ServiceException
     *         framework exception
     */
    void modifyPeople(Person people) throws ServiceException;

    /**
     * delete people by pk
     *
     * @param peopleId
     *         pk
     * @throws ServiceException
     *         if the target people is the operator
     */
    void deletePeople(String peopleId) throws ServiceException;

    /**
     * get people by code
     *
     * @param code
     *         people code
     * @return entity
     * @throws ServiceException
     *         framework exception
     */
    Person getByCode(String code) throws ServiceException;

    /**
     * count how many people in a department
     *
     * @param department
     *         target department
     * @return the people count
     * @throws ServiceException
     *         framework exception
     */
    long getPeopleCountByDepartment(Department department) throws ServiceException;

    /**
     * get the people list in a department
     *
     * @param departmentId
     *         target department pk
     * @return the list of people
     * @throws ServiceException
     *         framework exception
     */
    List<Person> getPeopleListByDepartment(String departmentId) throws ServiceException;

    /**
     * reset target people's password
     *
     * @param peopleId
     *         pk
     * @throws ServiceException
     *         framework exception
     */
    void resetPassword(String peopleId) throws ServiceException;

    /**
     * unlock a people's account
     *
     * @param peopleId
     *         pk
     * @throws ServiceException
     *         if the status of people is not locked
     */
    void unlock(String peopleId) throws ServiceException;

    /**
     * modify people's login password
     *
     * @param people
     *         target people
     * @param oldPwd
     *         old password
     * @param newPwd
     *         new password
     * @throws ServiceException
     *         if the old password is not correct
     */
    void modifyPassword(Person people, String oldPwd, String newPwd) throws ServiceException;
}
