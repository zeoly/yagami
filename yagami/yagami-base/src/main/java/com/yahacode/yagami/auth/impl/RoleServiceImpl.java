package com.yahacode.yagami.auth.impl;

import com.yahacode.yagami.auth.dao.PeopleRoleRelDao;
import com.yahacode.yagami.auth.dao.RoleDao;
import com.yahacode.yagami.auth.model.PeopleRoleRelation;
import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.pd.model.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

    private RoleDao roleDao;

    private PeopleRoleRelDao peopleRoleRelDao;

    @Override
    public List<Role> getAllRoleList() throws BizfwServiceException {
        return roleDao.list();
    }

    @Transactional
    @Override
    public String addRole(Role role) throws BizfwServiceException {
        List<Role> tmpRole = queryByFieldAndValue(Role.COLUMN_NAME, role.getName());
        if (ListUtils.isNotEmpty(tmpRole)) {
            throw new BizfwServiceException(ADD_FAIL_EXISTED);
        }
        role.init(getLoginPeople().getCode());
        logger.info("{}新增角色{}", role.getUpdateBy(), role.getName());
        return save(role);
    }

    @Transactional
    @Override
    public void modify(Role role) throws BizfwServiceException {
        List<Role> tmpRole = queryByFieldAndValue(Role.COLUMN_NAME, role.getName());
        if (ListUtils.isNotEmpty(tmpRole) && !role.getIdBfRole().equals(tmpRole.get(0).getIdBfRole())) {
            throw new BizfwServiceException(MOD_FAIL_EXISTED);
        }
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
            peopleRoleRelDao.save(peopleRoleRelation);
        }
    }

    @Override
    public List<Role> getRoleListByPeople(String peopleId) throws BizfwServiceException {
        List<Role> roleList = new ArrayList<>();
        List<PeopleRoleRelation> relationList = peopleRoleRelDao.queryByFieldAndValue(PeopleRoleRelation
                .COLUMN_PEOPLE_ID, peopleId);
        for (PeopleRoleRelation relation : relationList) {
            Role role = queryById(relation.getRoleId());
            roleList.add(role);
        }
        return roleList;
    }

    @Override
    public long countPeopleByRole(Role role) throws BizfwServiceException {
        return peopleRoleRelDao.getCountByFieldAndValue(PeopleRoleRelation.COLUMN_ROLE_ID, role.getIdBfRole());
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
        peopleRoleRelDao.deleteByFieldAndValue(PeopleRoleRelation.COLUMN_PEOPLE_ID, people.getIdBfPeople());
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
    public BaseDao<Role> getBaseDao() {
        return roleDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired
    public void setPeopleRoleRelDao(PeopleRoleRelDao peopleRoleRelDao) {
        this.peopleRoleRelDao = peopleRoleRelDao;
    }
}
