package com.yahacode.yagami.auth.service;

import java.util.List;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.pd.model.Person;

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
     * @throws ServiceException
     *         framework exception
     */
    List<Role> getAllRoleList() throws ServiceException;

    /**
     * add a role
     *
     * @param role
     *         entity
     * @throws ServiceException
     *         framework exception
     */
    String addRole(Role role) throws ServiceException;

    /**
     * modify role's name and description
     *
     * @param role
     *         entity
     * @throws ServiceException
     *         if the same role name is exists
     */
    void modify(Role role) throws ServiceException;

    /**
     * delete role by primary key
     *
     * @param roleId
     *         primary key
     * @throws ServiceException
     *         if the role is not exists;
     *         if the role has relation with any people;
     */
    void deleteRole(String roleId) throws ServiceException;

    /**
     * get the role list of a people
     *
     * @param peopleId
     *         people pk
     * @return role list
     * @throws ServiceException
     *         framework exception
     */
    List<Role> getRoleListByPeople(String peopleId) throws ServiceException;

    /**
     * set the role list of a people, use the roleIdList list.
     *
     * @param people
     *         entity
     * @throws ServiceException
     *         if the role is not exists
     */
    void setRoleOfPeople(Person people) throws ServiceException;

    /**
     * count how many people have the role
     *
     * @param role
     *         entity
     * @return the people count
     * @throws ServiceException
     *         framework exception
     */
    long countPeopleByRole(Role role) throws ServiceException;

    /**
     * get the role by role name.
     *
     * @param name
     *         role name
     * @return role entity
     * @throws ServiceException
     *         framework exception
     */
    Role getByName(String name) throws ServiceException;

}
