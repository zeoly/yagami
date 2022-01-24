package com.yahacode.yagami.core.service.impl;

import com.yahacode.yagami.core.model.PersonRoleRelation;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.repository.PersonRoleRelRepository;
import com.yahacode.yagami.core.repository.RoleRepository;
import com.yahacode.yagami.core.service.RoleService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.core.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.yahacode.yagami.base.consts.ErrorCode.Auth.Role.ADD_FAIL_EXISTED;
import static com.yahacode.yagami.base.consts.ErrorCode.Auth.Role.DEL_FAIL_WITH_PEOPLE;
import static com.yahacode.yagami.base.consts.ErrorCode.Auth.Role.MOD_FAIL_EXISTED;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.SET_ROLE_REL_FAIL_NOT_FOUND;

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

    @Autowired
    private PersonRoleRelRepository peopleRoleRelRepository;

    @Override
    public String addRole(Role role) throws ServiceException {
        Person person = getLoginPerson();
        log.info("{} add role {} start", person.getCode(), role.getName());
        Role tmpRole = findByName(role.getName());
        if (null != tmpRole) {
            log.warn("{} add role fail: {} already exists", person.getCode(), role.getName());
            throw new ServiceException(ADD_FAIL_EXISTED);
        }
        String id = initAndSave(role);
        log.info("{} add role {} end", person.getCode(), role.getName());
        return id;
    }

    @Override
    public void modify(Role role) throws ServiceException {
        Person person = getLoginPerson();
        Role oldRole = findById(role.getId());
        log.info("{} modify role {} to {} start", person.getCode(), oldRole.getName(), role.getName());
        Role tmpRole = findByName(role.getName());
        if (null != tmpRole) {
            log.warn("{} modify role fail: {} already exists", person.getCode(), role.getName());
            throw new ServiceException(MOD_FAIL_EXISTED);
        }
        updateById(role);
        log.info("{} modify role {} to {} end", person.getCode(), oldRole.getName(), role.getName());
    }

    @Override
    public void deleteRole(String roleId) throws ServiceException {
        Person person = getLoginPerson();
        Role role = findById(roleId);
        if (role == null) {
            return;
        }
        log.info("{} delete role {} start", person.getCode(), role.getName());
        checkCanDeleteRole(roleId);
        deleteById(roleId);
        log.info("{} delete role {} end", person.getCode(), role.getName());
    }

    @Transactional
    @Override
    public void saveRoleOfPerson(String personCode, List<String> roleIds) throws ServiceException {
        Person operator = getLoginPerson();
        deleteAllRoleByPersonCode(personCode);
        for (String id : roleIds) {
            Role role = findById(id);
            if (role == null) {
                throw new ServiceException(SET_ROLE_REL_FAIL_NOT_FOUND, id);
            }
            PersonRoleRelation peopleRoleRelation = new PersonRoleRelation(operator.getCode(), personCode, id);
            peopleRoleRelRepository.save(peopleRoleRelation);
        }
    }

    @Override
    public List<Role> getRoleListByPeople(String personCode) {
        List<Role> roleList = new ArrayList<>();
        List<PersonRoleRelation> relationList = peopleRoleRelRepository.findByPersonCode(personCode);
        for (PersonRoleRelation relation : relationList) {
            Role role = findById(relation.getRoleId());
            roleList.add(role);
        }
        return roleList;
    }

    @Override
    public long countPersonByRoleId(String roleId) {
        return peopleRoleRelRepository.countByRoleId(roleId);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    /**
     * delete all roles of a person
     *
     * @param personCode person code
     */
    private void deleteAllRoleByPersonCode(String personCode) {
        peopleRoleRelRepository.deleteByPersonCode(personCode);
    }

    /**
     * check if the role can be deleted, will throw an exception if not
     *
     * @param roleId role id
     * @throws ServiceException if any person has the role
     */
    private void checkCanDeleteRole(String roleId) throws ServiceException {
        long personCount = countPersonByRoleId(roleId);
        if (personCount > 0) {
            throw new ServiceException(DEL_FAIL_WITH_PEOPLE);
        }
    }

    @Override
    public JpaRepository<Role, String> getBaseRepository() {
        return roleRepository;
    }

}
