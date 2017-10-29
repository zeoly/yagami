package com.yahacode.yagami.auth.service;

import java.util.List;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;

/**
 * Role service
 *
 * @author zengyongli
 */
public interface RoleService extends BaseService<Role> {

    /**
     * get the list of all role
     *
     * @return list of all role
     * @throws BizfwServiceException
     *         framework exception
     */
    List<Role> getAllRoleList() throws BizfwServiceException;

    /**
     * add a role
     *
     * @param role
     *         entity
     * @throws BizfwServiceException
     *         framework exception
     */
    String addRole(Role role) throws BizfwServiceException;

    /**
     * modify role's name and description
     *
     * @param role
     *         entity
     * @throws BizfwServiceException
     *         if the same role name is exists
     */
    void modify(Role role) throws BizfwServiceException;

    /**
     * delete role by primary key
     *
     * @param roleId
     *         primary key
     * @throws BizfwServiceException
     *         if the role is not exists;
     *         if the role has relation with any people;
     */
    void deleteRole(String roleId) throws BizfwServiceException;

    /**
     * get the role list of a people
     *
     * @param peopleId
     *         people pk
     * @return role list
     * @throws BizfwServiceException
     *         framework exception
     */
    List<Role> getRoleListByPeople(String peopleId) throws BizfwServiceException;

    /**
     * set the role list of a people, use the roleIdList list.
     *
     * @param people
     *         entity
     * @throws BizfwServiceException
     *         if the role is not exists
     */
    void setRoleOfPeople(People people) throws BizfwServiceException;

    /**
     * count how many people have the role
     *
     * @param role
     *         entity
     * @return the people count
     * @throws BizfwServiceException
     *         framework exception
     */
    long countPeopleByRole(Role role) throws BizfwServiceException;

}
