package com.yahacode.yagami.auth.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yahacode.yagami.auth.dao.PeopleRoleRelDao;
import com.yahacode.yagami.auth.dao.RoleDao;
import com.yahacode.yagami.auth.model.PeopleRoleRelation;
import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.pd.model.People;

/**
 * 角色服务实现类
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
            throw new BizfwServiceException(ErrorCode.Auth.Role.ADD_FAIL_EXISTED);
        }
        logger.info("{}新增角色{}", role.getUpdateBy(), role.getName());
        return save(role);
    }

    @Transactional
    @Override
    public void modify(Role role) throws BizfwServiceException {
        List<Role> tmpRole = queryByFieldAndValue(Role.COLUMN_NAME, role.getName());
        if (ListUtils.isNotEmpty(tmpRole) && !role.getIdBfRole().equals(tmpRole.get(0).getIdBfRole())) {
            throw new BizfwServiceException(ErrorCode.Auth.Role.MOD_FAIL_EXISTED);
        }
        Role dbRole = queryById(role.getIdBfRole());
        dbRole.setName(role.getName());
        dbRole.setDescription(role.getDescription());
        dbRole.update(role.getUpdateBy());
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
                throw new BizfwServiceException(ErrorCode.PeopleDept.People.SET_ROLE_REL_FAIL_NOT_FOUND, id);
            }
            PeopleRoleRelation peopleRoleRelation = new PeopleRoleRelation(people.getUpdateBy(), people.getIdBfPeople
                    (), role.getIdBfRole());
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
     * 根据人员删除人员角色关联关系
     *
     * @param people
     *         人员
     * @throws BizfwServiceException
     *         业务异常
     */
    private void deletePeopleRoleRelation(People people) throws BizfwServiceException {
        peopleRoleRelDao.deleteByFieldAndValue(PeopleRoleRelation.COLUMN_PEOPLE_ID, people.getIdBfPeople());
    }

    /**
     * 检查角色是否可删除
     *
     * @param role
     *         角色
     * @throws BizfwServiceException
     *         角色关联人员异常
     */
    private void checkCanDeleteRole(Role role) throws BizfwServiceException {
        long peopleCount = countPeopleByRole(role);
        if (peopleCount > 0) {
            throw new BizfwServiceException(ErrorCode.Auth.Role.DEL_FAIL_WITH_PEOPLE);
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
