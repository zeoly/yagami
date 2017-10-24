package com.yahacode.yagami.auth.service;

import java.util.List;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;

/**
 * 角色服务接口
 *
 * @author zengyongli
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 获取所有角色列表
     *
     * @return 角色列表
     * @throws BizfwServiceException
     *         业务异常
     */
    List<Role> getAllRoleList() throws BizfwServiceException;

    /**
     * 添加角色
     *
     * @param role
     *         角色
     * @throws BizfwServiceException
     *         业务异常
     */
    String addRole(Role role) throws BizfwServiceException;

    /**
     * 修改角色
     *
     * @param role
     *         角色
     * @throws BizfwServiceException
     *         业务异常
     */
    void modify(Role role) throws BizfwServiceException;

    /**
     * 根据id删除角色
     *
     * @param roleId
     *         角色id
     * @throws BizfwServiceException
     *         业务异常
     */
    void deleteRole(String roleId) throws BizfwServiceException;

    /**
     * 获取人员关联的角色列表
     *
     * @param peopleId
     *         人员id
     * @return 角色列表
     * @throws BizfwServiceException
     *         业务异常
     */
    List<Role> getRoleListByPeople(String peopleId) throws BizfwServiceException;

    /**
     * 设置人员关联的角色列表
     *
     * @param people
     *         人员
     * @throws BizfwServiceException
     *         业务异常
     */
    void setRoleOfPeople(People people) throws BizfwServiceException;

    /**
     * 获取角色关联的人员数量
     *
     * @param role
     *         角色
     * @return 关联数量
     * @throws BizfwServiceException
     *         业务异常
     */
    long countPeopleByRole(Role role) throws BizfwServiceException;

}
