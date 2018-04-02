package com.yahacode.yagami.pd.service.impl;

import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.LogUtils;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.People;
import com.yahacode.yagami.pd.repository.PeopleRepository;
import com.yahacode.yagami.auth.repository.PeopleRoleRelRepository;
import com.yahacode.yagami.pd.service.DepartmentService;
import com.yahacode.yagami.pd.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.ADD_FAIL_EXISTED;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.ADD_FAIL_WITHOUT_DEPT;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.DEL_FAIL_SELF;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.UNLOCK_FAIL_STATUS_ERR;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.UPDATE_FAIL_PWD_ERR;

/**
 * PeopleService implementation
 *
 * @author zengyongli
 */
@Service
public class PeopleServiceImpl extends BaseServiceImpl<People> implements PeopleService {

    private DepartmentService departmentService;

    private RoleService roleService;

    private PeopleRepository peopleRepository;

    private PeopleRoleRelRepository peopleRoleRelRepository;

    private static final int COUNTER_ZERO = 0;

    @Transactional
    @Override
    public String addPeople(People people) throws BizfwServiceException {
        People operator = getLoginPeople();
        LogUtils.info("{}新增人员{}", operator.getCode(), people.getCode());
        People tmpPeople = getByCode(people.getCode());
        if (tmpPeople != null) {
            LogUtils.error("{}新增人员{}失败，人员已存在", operator.getCode(), people.getCode());
            throw new BizfwServiceException(ADD_FAIL_EXISTED, people.getCode());
        }
        Department department = departmentService.queryById(people.getDepartmentId());
        if (department == null) {
            LogUtils.error("{}新增人员{}失败，机构{}为空", operator.getCode(), people.getCode(), people.getDepartmentId());
            throw new BizfwServiceException(ADD_FAIL_WITHOUT_DEPT);
        }
        people.init(operator.getCode());
        people.setErrorCount(COUNTER_ZERO);
        people.setStatus(People.STATUS_NORMAL);
        people.setPassword(StringUtils.encryptMD5(people.getCode() + StringUtils.encryptMD5(PropertiesUtils
                .getSysConfig("default.pwd"))));
        String id = save(people).getDepartmentId();
        roleService.setRoleOfPeople(people);
        return id;
    }

    @Transactional
    @Override
    public void modifyPeople(People people) throws BizfwServiceException {
        People operator = getLoginPeople();
        LogUtils.info("{}修改人员{}操作开始", operator.getCode(), people.getCode());
        people.update(operator.getCode());
        roleService.setRoleOfPeople(people);
        update(people);
        LogUtils.info("{}修改人员{}操作完成", operator.getCode(), people.getCode());
    }

    @Transactional
    @Override
    public void deletePeople(String peopleId) throws BizfwServiceException {
        People operator = getLoginPeople();
        People target = queryById(peopleId);
        LogUtils.info("{}删除人员{}操作开始", operator.getCode(), target.getCode());
        if (target.getCode().equals(operator.getCode())) {
            LogUtils.error("{}删除自己，失败", operator.getCode());
            throw new BizfwServiceException(DEL_FAIL_SELF);
        }
        delete(peopleId);
        peopleRoleRelRepository.deleteByPeopleId(peopleId);
        LogUtils.info("{}删除人员{}操作完成", operator.getCode(), target.getCode());
    }

    @Transactional
    @Override
    public People getByCode(String code) throws BizfwServiceException {
        return peopleRepository.findByCode(code);
    }

    @Override
    public long getPeopleCountByDepartment(Department department) throws BizfwServiceException {
        return peopleRepository.countByDepartmentId(department.getIdBfDepartment());
    }

    @Override
    public List<People> getPeopleListByDepartment(String departmentId) throws BizfwServiceException {
        return peopleRepository.findByDepartmentId(departmentId);
    }

    @Override
    public void resetPassword(String peopleId) throws BizfwServiceException {
        People people = queryById(peopleId);
        People operator = getLoginPeople();
        LogUtils.info("{}重置密码{}操作开始", operator.getCode(), people.getCode());
        people.update(operator.getCode());
        people.setPassword(StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd")));
        people.setErrorCount(COUNTER_ZERO);
        update(people);
        LogUtils.info("{}重置密码{}操作结束", operator.getCode(), people.getCode());
    }

    @Override
    public void unlock(String peopleId) throws BizfwServiceException {
        People people = queryById(peopleId);
        People operator = getLoginPeople();
        LogUtils.info("{}解锁人员{}操作开始", operator.getCode(), people.getCode());
        if (!People.STATUS_LOCKED.equals(people.getStatus())) {
            LogUtils.error("{}解锁人员{}操作失败, 人员状态为{}", operator.getCode(), people.getCode(), people.getStatus());
            throw new BizfwServiceException(UNLOCK_FAIL_STATUS_ERR);
        }
        people.update(operator.getCode());
        people.setStatus(People.STATUS_NORMAL);
        people.setErrorCount(COUNTER_ZERO);
        update(people);
        LogUtils.info("{}解锁人员{}操作结束", operator.getCode(), people.getCode());
    }

    @Override
    public void modifyPassword(People people, String oldPwd, String newPwd) throws BizfwServiceException {
        LogUtils.info("{}修改密码操作开始", people.getCode());
        if (!oldPwd.equals(people.getPassword())) {
            throw new BizfwServiceException(UPDATE_FAIL_PWD_ERR);
        }
        people.setPassword(StringUtils.encryptMD5(people.getCode() + StringUtils.encryptMD5(newPwd)));
        update(people);
        LogUtils.info("{}修改密码操作结束", people.getCode());
    }

    @Override
    public JpaRepository<People, String> getBaseRepository() {
        return peopleRepository;
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
    public void setPeopleRepository(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Autowired
    public void setPeopleRoleRelRepository(PeopleRoleRelRepository peopleRoleRelRepository) {
        this.peopleRoleRelRepository = peopleRoleRelRepository;
    }

}
