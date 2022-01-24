package com.yahacode.yagami.core.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.core.model.Person;

import java.util.List;

/**
 * person service
 *
 * @author zengyongli
 */
public interface PersonService extends BaseService<Person> {

    /**
     * add a new people
     *
     * @param people entity
     * @return pk
     * @throws ServiceException if the same name people is exists;
     *                          if the people's department is not exists;
     */
    String addPeople(Person people) throws ServiceException;

    /**
     * modify people's roles
     *
     * @param person target person
     * @throws if the person is not exists
     */
    void modifyPerson(Person person) throws ServiceException;

    /**
     * delete people by pk
     *
     * @param peopleId pk
     * @throws ServiceException if the target people is the operator
     */
    void deletePeople(String peopleId) throws ServiceException;

    /**
     * get person by code
     *
     * @param code target person code
     * @return person entity
     */
    Person findByCode(String code);

    /**
     * count how many people in a department
     *
     * @param departmentCode target department code
     * @return the person count
     */
    long countPersonByDepartment(String departmentCode);

    /**
     * get the people list in a department
     *
     * @param departmentCode target department code
     * @return the list of people
     */
    List<Person> findByDepartment(String departmentCode);

    /**
     * reset target people's password
     *
     * @param peopleId pk
     * @throws ServiceException framework exception
     */
    void resetPassword(String peopleId) throws ServiceException;

    /**
     * unlock a person's account
     *
     * @param personCode target person code
     * @throws ServiceException if the status of person is not locked
     */
    void unlock(String personCode) throws ServiceException;

    /**
     * modify person's login password
     *
     * @param personCode target person code
     * @param oldPwd     old password
     * @param newPwd     new password
     * @throws ServiceException if the old password is not correct
     */
    void modifyPassword(String personCode, String oldPwd, String newPwd) throws ServiceException;
}
