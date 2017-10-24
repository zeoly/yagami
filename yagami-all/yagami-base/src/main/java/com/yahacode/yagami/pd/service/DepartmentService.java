package com.yahacode.yagami.pd.service;

import java.util.List;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.Department;

/**
 * 机构服务接口
 *
 * @author zengyongli
 */
public interface DepartmentService extends BaseService<Department> {

    /**
     * 增加机构
     *
     * @param department
     *         机构
     * @throws BizfwServiceException
     *         业务异常
     */
    void addDepartment(Department department) throws BizfwServiceException;

    /**
     * 修改机构，更新机构名、机构代码
     *
     * @param department
     *         机构
     * @throws BizfwServiceException
     *         业务异常
     */
    void modifyDepartment(Department department) throws BizfwServiceException;

    /**
     * 删除机构（根据id）
     *
     * @param department
     *         机构
     * @throws BizfwServiceException
     *         业务异常
     */
    void deleteDepartment(Department department) throws BizfwServiceException;

    /**
     * 删除所有上级机构关联关系
     *
     * @param department
     *         机构
     * @throws BizfwServiceException
     *         业务异常
     */
    void deleteUpperDepartmentRelation(Department department) throws BizfwServiceException;

    /**
     * 根据机构代码获取机构信息
     *
     * @param code
     *         机构代码
     * @return 机构
     * @throws BizfwServiceException
     *         业务异常
     */
    Department queryByCode(String code) throws BizfwServiceException;

    /**
     * 判断是否机构下存在子机构
     *
     * @param department
     *         机构
     * @return boolean
     * @throws BizfwServiceException
     *         业务异常
     */
    boolean hasChildDepartment(Department department) throws BizfwServiceException;

    /**
     * 判断机构下是否存在人员
     *
     * @param department
     *         机构
     * @return boolean
     * @throws BizfwServiceException
     *         业务异常
     */
    boolean hasPeople(Department department) throws BizfwServiceException;

    /**
     * 获取父机构
     *
     * @param department
     *         机构
     * @return 父机构
     * @throws BizfwServiceException
     *         业务异常
     */
    Department getParentDepartment(Department department) throws BizfwServiceException;

    /**
     * 获取子机构列表
     *
     * @param deparmentId
     *         机构id
     * @return 子机构列表
     * @throws BizfwServiceException
     *         业务异常
     */
    List<Department> getChildDepartmentList(String deparmentId) throws BizfwServiceException;

    /**
     * 获取所有上级机构信息（包含跨级）
     *
     * @param deparmentId
     *         机构id
     * @return 父机构列表
     * @throws BizfwServiceException
     *         业务异常
     */
    List<Department> getAllParentDeptmentList(String deparmentId) throws BizfwServiceException;

    /**
     * 获取所有子机构列表（包含跨级）
     *
     * @param deparmentId
     *         机构id
     * @return 子机构列表
     * @throws BizfwServiceException
     *         业务异常
     */
    List<Department> getAllChildDeptmentList(String deparmentId) throws BizfwServiceException;

    /**
     * 获取当前机构下的机构树
     *
     * @param deparmentId
     *         机构id
     * @return 机构树
     * @throws BizfwServiceException
     *         业务异常
     */
    Department getDepartmentTreeByDepartmentId(String deparmentId) throws BizfwServiceException;

}
