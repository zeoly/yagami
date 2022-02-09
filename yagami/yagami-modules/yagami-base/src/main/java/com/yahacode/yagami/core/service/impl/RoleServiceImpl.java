package com.yahacode.yagami.core.service.impl;

import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.repository.RoleRepository;
import com.yahacode.yagami.core.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yahacode.yagami.base.consts.ErrorCode.Auth.Role.*;

/**
 * RoleService implementation
 *
 * @author zengyongli
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAllByOrderByName();
    }

    @Override
    public String addRole(Role role) throws ServiceException {
        Person person = getLoginPerson();
        log.info("{} add role [{}] start", person.getCode(), role.getName());
        Role tmpRole = findByName(role.getName());
        if (null != tmpRole) {
            log.warn("{} add role fail: name [{}] already exists", person.getCode(), role.getName());
            throw new ServiceException(ADD_FAIL_EXISTED);
        }
        String id = initAndSave(role);
        log.info("{} add role [{}] end", person.getCode(), role.getName());
        return id;
    }

    @Override
    public void modify(Role role) throws ServiceException {
        Person person = getLoginPerson();
        Role oldRole = findById(role.getId());
        log.info("{} modify role [{}] to [{}] start", person.getCode(), oldRole.getName(), role.getName());
        Role tmpRole = findByName(role.getName());
        if (null != tmpRole) {
            log.warn("{} modify role fail: name [{}] already exists", person.getCode(), role.getName());
            throw new ServiceException(MOD_FAIL_EXISTED);
        }
        updateById(role);
        log.info("{} modify role [{}] to [{}] end", person.getCode(), oldRole.getName(), role.getName());
    }

    @Override
    public void deleteRole(String roleId) throws ServiceException {
        Person person = getLoginPerson();
        Role role = findById(roleId);
        if (role == null) {
            return;
        }
        log.info("{} delete role [{}] start", person.getCode(), role.getName());
        checkCanDeleteRole(roleId);
        deleteById(roleId);
        log.info("{} delete role [{}] end", person.getCode(), role.getName());
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional
    @Override
    public Role findById(String id) {
        Role role = super.findById(id);
        log.info("role [{}] has {} menus", id, role.getMenuList().size());
        return role;
    }

    /**
     * check if the role can be deleted, will throw an exception if not
     *
     * @param roleId role id
     * @throws ServiceException if any person has the role
     */
    @Transactional
    private void checkCanDeleteRole(String roleId) throws ServiceException {
        Role role = findById(roleId);
        if (role.getPersonList().size() > 0) {
            throw new ServiceException(DEL_FAIL_WITH_PEOPLE);
        }
    }

    @Override
    public JpaRepository<Role, String> getBaseRepository() {
        return roleRepository;
    }

}
