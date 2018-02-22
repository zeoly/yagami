package com.yahacode.yagami.pd.impl;

import com.yahacode.yagami.auth.dao.PeopleRoleRelDao;
import com.yahacode.yagami.auth.model.PeopleRoleRelation;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseDao;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.pd.dao.PeopleDao;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.People;
import com.yahacode.yagami.pd.service.DepartmentService;
import com.yahacode.yagami.pd.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.ADD_FAIL_EXISTED;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.ADD_FAIL_WITHOUT_DEPT;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.DEL_FAIL_SELF;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.QUERY_FAIL_DUPLICATED;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.UNLOCK_FAIL_STATUS_ERR;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.UPDATE_FAIL_PWD_ERR;

/**
 * PeopleService implementation
 *
 * @author zengyongli
 */
@Service
public class PeopleServiceImpl extends BaseServiceImpl<People> implements PeopleService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private DepartmentService departmentService;

    private RoleService roleService;

    private PeopleDao peopleDao;

    private PeopleRoleRelDao peopleRoleRelDao;

    @Transactional
    @Override
    public String addPeople(People people) throws BizfwServiceException {
        People operator = getLoginPeople();
        logger.info("{}新增人员{}", operator.getCode(), people.getCode());
        People tmpPeople = getByCode(people.getCode());
        if (tmpPeople != null) {
            logger.error("{}新增人员{}失败，人员已存在", operator.getCode(), people.getCode());
            throw new BizfwServiceException(ADD_FAIL_EXISTED, people.getCode());
        }
        Department department = departmentService.queryById(people.getDepartmentId());
        if (department == null) {
            logger.error("{}新增人员{}失败，机构{}为空", operator.getCode(), people.getCode(), people.getDepartmentId());
            throw new BizfwServiceException(ADD_FAIL_WITHOUT_DEPT);
        }
        people.init(operator.getCode());
        people.setErrorCount(0);
        people.setStatus(People.STATUS_NORMAL);
        people.setPassword(StringUtils.encryptMD5(people.getCode() + StringUtils.encryptMD5(PropertiesUtils
                .getSysConfig("default.pwd"))));
        String id = save(people);
        roleService.setRoleOfPeople(people);
        return id;
    }

    @Transactional
    @Override
    public void modifyPeople(People people) throws BizfwServiceException {
        People operator = getLoginPeople();
        logger.info("{}修改人员{}操作开始", operator.getCode(), people.getCode());
        people.update(operator.getCode());
        roleService.setRoleOfPeople(people);
        update(people);
        logger.info("{}修改人员{}操作完成", operator.getCode(), people.getCode());
    }

    @Transactional
    @Override
    public void deletePeople(People people) throws BizfwServiceException {
        People operator = getLoginPeople();
        logger.info("{}删除人员{}操作开始", operator.getCode(), people.getCode());
        if (people.getCode().equals(people.getUpdateBy())) {
            logger.error("{}删除自己，失败", operator.getCode());
            throw new BizfwServiceException(DEL_FAIL_SELF);
        }
        delete(people.getIdBfPeople());
        peopleRoleRelDao.deleteByFieldAndValue(PeopleRoleRelation.COLUMN_PEOPLE_ID, people.getIdBfPeople());
        logger.info("{}删除人员{}操作完成", people.getCode(), people.getCode());
    }

    @Transactional
    @Override
    public People getByCode(String code) throws BizfwServiceException {
        List<People> peopleInfoList = queryByFieldAndValue(People.COLUMN_CODE, code);
        if (peopleInfoList.isEmpty()) {
            return null;
        }
        if (peopleInfoList.size() > 1) {
            throw new BizfwServiceException(QUERY_FAIL_DUPLICATED);
        }
        return peopleInfoList.get(0);
    }

    @Override
    public long getPeopleCountByDepartment(Department department) throws BizfwServiceException {
        return peopleDao.getCountByFieldAndValue(People.COLUMN_DEPARTMENT_ID, department.getIdBfDepartment());
    }

    @Override
    public List<People> getPeopleListByDepartment(String departmentId) throws BizfwServiceException {
        return queryByFieldAndValue(People.COLUMN_DEPARTMENT_ID, departmentId);
    }

    @Override
    public void unlock(People people) throws BizfwServiceException {
        People operator = getLoginPeople();
        logger.info("{}解锁人员{}操作开始", operator.getCode(), people.getCode());
        if (!People.STATUS_LOCKED.equals(people.getStatus())) {
            logger.error("{}解锁人员{}操作失败, 人员状态为{}", operator.getCode(), people.getCode(), people.getStatus());
            throw new BizfwServiceException(UNLOCK_FAIL_STATUS_ERR);
        }
        people.update(operator.getCode());
        people.setStatus(People.STATUS_NORMAL);
        people.setPassword(StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd")));
        people.setErrorCount(0);
        update(people);
        logger.info("{}解锁人员{}操作结束", operator.getCode(), people.getCode());
    }

    @Override
    public void modifyPassword(People people, String oldPwd, String newPwd) throws BizfwServiceException {
        logger.info("{}修改密码操作开始", people.getCode());
        if (!oldPwd.equals(people.getPassword())) {
            throw new BizfwServiceException(UPDATE_FAIL_PWD_ERR);
        }
        people.setPassword(StringUtils.encryptMD5(people.getCode() + StringUtils.encryptMD5(newPwd)));
        update(people);
        logger.info("{}修改密码操作结束", people.getCode());
    }

    @Override
    public BaseDao<People> getBaseDao() {
        return peopleDao;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPeopleDao(PeopleDao peopleDao) {
        this.peopleDao = peopleDao;
    }

    @Autowired
    public void setPeopleRoleRelDao(PeopleRoleRelDao peopleRoleRelDao) {
        this.peopleRoleRelDao = peopleRoleRelDao;
    }
}
