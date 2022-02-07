package com.yahacode.yagami.core.service;

import com.yahacode.yagami.base.BaseService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.core.model.Role;

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
     * @throws ServiceException if the same role name is exists
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
     * get the role by name.
     *
     * @param name role name
     * @return role entity
     */
    Role findByName(String name);

}
