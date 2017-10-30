package com.yahacode.yagami.pd.service;

import java.util.List;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.Department;

/**
 * department service
 *
 * @author zengyongli
 */
public interface DepartmentService extends BaseService<Department> {

    /**
     * add a new department
     *
     * @param department
     *         entity
     * @throws BizfwServiceException
     *         framework exception
     */
    void addDepartment(Department department) throws BizfwServiceException;

    /**
     * modify department's name and code
     *
     * @param department
     *         target department
     * @throws BizfwServiceException
     *         framework exception
     */
    void modifyDepartment(Department department) throws BizfwServiceException;

    /**
     * delete department by pk
     *
     * @param department
     *         target department
     * @throws BizfwServiceException
     *         if the department is not exists;
     *         if the department contain any child department or people;
     */
    void deleteDepartment(Department department) throws BizfwServiceException;

    /**
     * get department by code
     *
     * @param code
     *         department code
     * @return entity
     * @throws BizfwServiceException
     *         framework exception
     */
    Department queryByCode(String code) throws BizfwServiceException;

    /**
     * if department has any child department
     *
     * @param department
     *         target department
     * @return boolean
     * @throws BizfwServiceException
     *         framework exception
     */
    boolean hasChildDepartment(Department department) throws BizfwServiceException;

    /**
     * if department contain any people
     *
     * @param department
     *         target department
     * @return boolean
     * @throws BizfwServiceException
     *         framework exception
     */
    boolean hasPeople(Department department) throws BizfwServiceException;

    /**
     * get the parent department
     *
     * @param department
     *         target department
     * @return parent department
     * @throws BizfwServiceException
     *         framework exception
     */
    Department getParentDepartment(Department department) throws BizfwServiceException;

    /**
     * get the child department list
     *
     * @param departmentId
     *         department pk
     * @return list of child departments
     * @throws BizfwServiceException
     *         framework exception
     */
    List<Department> getChildDepartmentList(String departmentId) throws BizfwServiceException;

    /**
     * get the tree structure of target department and its child departments
     *
     * @param departmentId
     *         primary key
     * @return target department of tree structure
     * @throws BizfwServiceException
     *         framework exception
     */
    Department getDepartmentTreeByDepartmentId(String departmentId) throws BizfwServiceException;

}
