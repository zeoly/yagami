package com.yahacode.yagami.auth.service.impl;

import com.yahacode.yagami.auth.model.PeopleRoleRelation;
import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.repository.PeopleRoleRelRepository;
import com.yahacode.yagami.auth.repository.RoleRepository;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.LogUtils;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.pd.model.People;
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
 * role service implementation
 *
 * @author zengyongli
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private PeopleRoleRelRepository peopleRoleRelRepository;

    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoleList() throws BizfwServiceException {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public String addRole(Role role) throws BizfwServiceException {
        Role tmpRole = getByName(role.getName());
        if (null != tmpRole) {
            throw new BizfwServiceException(ADD_FAIL_EXISTED);
        }
        role.init(getLoginPeople().getCode());
        LogUtils.info("{}新增角色{}", role.getUpdateBy(), role.getName());
        return save(role);
    }

    @Transactional
    @Override
    public void modify(Role role) throws BizfwServiceException {
        Role tmpRole = getByName(role.getName());
        if (null != tmpRole) {
            throw new BizfwServiceException(MOD_FAIL_EXISTED);
        }
        LogUtils.info("{}修改角色{}", role.getUpdateBy(), role.getName());
        Role dbRole = queryById(role.getIdBfRole());
        dbRole.setName(role.getName());
        dbRole.setDescription(role.getDescription());
        dbRole.update(getLoginPeople().getCode());
        update(dbRole);
    }

    @Transactional
    @Override
    public void deleteRole(String roleId) throws BizfwServiceException {
        Role role = queryById(roleId);
        checkObjectNotNull(role, "角色[" + roleId + "]", "删除角色");
        checkCanDeleteRole(role);
        delete(role.getIdBfRole());
    }

    @Transactional
    @Override
    public void setRoleOfPeople(People people) throws BizfwServiceException {
        deletePeopleRoleRelation(people);
        for (String id : people.getRoleIdList()) {
            Role role = queryById(id);
            if (role == null) {
                throw new BizfwServiceException(SET_ROLE_REL_FAIL_NOT_FOUND, id);
            }
            PeopleRoleRelation peopleRoleRelation = new PeopleRoleRelation(getLoginPeople().getCode(), people
                    .getIdBfPeople(), role.getIdBfRole());
            peopleRoleRelRepository.save(peopleRoleRelation);
        }
    }

    @Override
    public List<Role> getRoleListByPeople(String peopleId) throws BizfwServiceException {
        List<Role> roleList = new ArrayList<>();
        List<PeopleRoleRelation> relationList = peopleRoleRelRepository.findByPeopleId(peopleId);
        for (PeopleRoleRelation relation : relationList) {
            Role role = queryById(relation.getRoleId());
            roleList.add(role);
        }
        return roleList;
    }

    @Override
    public long countPeopleByRole(Role role) throws BizfwServiceException {
        return peopleRoleRelRepository.countByRoleId(role.getIdBfRole());
    }

    @Override
    public Role getByName(String name) throws BizfwServiceException {
        return roleRepository.findByName(name);
    }

    /**
     * delete all PeopleRoleRelation of a people
     *
     * @param people
     *         entity
     * @throws BizfwServiceException
     *         framework exception
     */
    private void deletePeopleRoleRelation(People people) throws BizfwServiceException {
        peopleRoleRelRepository.deleteByPeopleId(people.getIdBfPeople());
    }

    /**
     * check if the role can be deleted, will throw an exception if not
     *
     * @param role
     *         entity
     * @throws BizfwServiceException
     *         if the role has any relation with people
     */
    private void checkCanDeleteRole(Role role) throws BizfwServiceException {
        long peopleCount = countPeopleByRole(role);
        if (peopleCount > 0) {
            throw new BizfwServiceException(DEL_FAIL_WITH_PEOPLE);
        }
    }

    @Override
    public JpaRepository<Role, String> getBaseRepository() {
        return roleRepository;
    }

    @Autowired
    public void setPeopleRoleRelRepository(PeopleRoleRelRepository peopleRoleRelRepository) {
        this.peopleRoleRelRepository = peopleRoleRelRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
