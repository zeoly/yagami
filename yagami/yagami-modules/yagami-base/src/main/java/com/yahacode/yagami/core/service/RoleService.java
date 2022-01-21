package com.yahacode.yagami.core.service;

import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.ServiceException;

import java.util.List;

/**
 * Role service
 *
 * @author zengyongli
 */
public interface RoleService extends BaseService<Role> {

    /**
     * add a role
     *
     * @param role entity
     * @throws ServiceException framework exception
     */
    String addRole(Role role) throws ServiceException;

    /**
     * modify role's name and description
     *
     * @param role entity
     * @throws ServiceException if the same role name is exists
     */
    void modify(Role role) throws ServiceException;

    /**
     * delete role by primary key
     *
     * @param roleId primary key
     * @throws ServiceException if the role is not exists;
     *                          if the role has relation with any people;
     */
    void deleteRole(String roleId) throws ServiceException;

    /**
     * get the roles of a person
     *
     * @param personCode people code
     * @return role list
     */
    List<Role> getRoleListByPeople(String personCode);

    /**
     * set the role list of a person
     *
     * @param personCode person role
     * @param roleIds all role's ids
     * @exception
     */
    void saveRoleOfPerson(String personCode, List<String> roleIds) throws ServiceException;

    /**
     * count how many people have the role
     *
     * @param roleId role primary key
     * @return the people count
     */
    long countPersonByRoleId(String roleId);

    /**
     * get the role by name.
     *
     * @param name role name
     * @return role entity
     */
    Role findByName(String name);

}
