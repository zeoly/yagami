package com.yahacode.yagami.core.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.core.model.Department;

import java.util.List;

/**
 * department service
 *
 * @author zengyongli
 */
public interface DepartmentService extends BaseService<Department> {

    /**
     * find department by department code
     *
     * @param code department code
     * @return department
     */
    Department findByCode(String code);

    /**
     * add a new department
     *
     * @param department target entity
     * @throws ServiceException if department code exists;
     *                          if the parent department code exists;
     */
    void addDepartment(Department department) throws ServiceException;

    /**
     * modify department's name and code
     *
     * @param department target department
     * @throws ServiceException framework exception
     */
    void modifyDepartment(Department department) throws ServiceException;

    /**
     * delete department by code
     *
     * @param code department code
     * @throws ServiceException if the department is not exists;
     *                          if the department contain any child department or people;
     */
    void deleteDepartment(String code) throws ServiceException;

    /**
     * if department has any child department
     *
     * @param code department code
     * @return whether exists children
     * @throws ServiceException framework exception
     */
    boolean hasChild(String code);

    /**
     * if department contain any person
     *
     * @param code department code
     * @return whether contain person
     * @throws ServiceException framework exception
     */
    boolean hasPerson(String code) throws ServiceException;

    /**
     * get the child department list
     *
     * @param code department code
     * @return list of child departments
     */
    List<Department> findByParentCode(String code);

}
