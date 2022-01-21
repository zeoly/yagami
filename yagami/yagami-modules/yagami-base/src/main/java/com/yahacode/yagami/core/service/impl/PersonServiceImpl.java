package com.yahacode.yagami.core.service.impl;

import com.yahacode.yagami.core.repository.PersonRoleRelRepository;
import com.yahacode.yagami.core.service.RoleService;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.core.model.Department;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.repository.PersonRepository;
import com.yahacode.yagami.core.service.DepartmentService;
import com.yahacode.yagami.core.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * PersonService implementation
 *
 * @author zengyongli
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private DepartmentService departmentService;

    private RoleService roleService;

    @Autowired
    private PersonRepository peopleRepository;

    @Autowired
    private PersonRoleRelRepository peopleRoleRelRepository;

    private static final int COUNTER_ZERO = 0;

    @Transactional
    @Override
    public String addPeople(Person person) throws ServiceException {
        Person operator = getLoginPerson();
        log.info("{} add person {} start", operator.getCode(), person.getCode());
        Person tmpPeople = getByCode(person.getCode());
        if (tmpPeople != null) {
            log.warn("add person fail: {} already exists", person.getCode());
            throw new ServiceException(ADD_FAIL_EXISTED, person.getCode());
        }
        Department department = departmentService.findByCode(person.getDepartmentCode());
        if (department == null) {
            log.warn("add person fail: department not exists. code {}", person.getDepartmentCode());
            throw new ServiceException(ADD_FAIL_WITHOUT_DEPT);
        }
        String id = initAndSave(person);
        roleService.setRoleOfPeople(people);
        return id;
    }

    @Transactional
    @Override
    public void modifyPeople(Person people) throws ServiceException {
        Person operator = getLoginPeople();
        LogUtils.info("{}修改人员{}操作开始", operator.getCode(), people.getCode());
        people.update(operator.getCode());
        roleService.setRoleOfPeople(people);
        update(people);
        LogUtils.info("{}修改人员{}操作完成", operator.getCode(), people.getCode());
    }

    @Transactional
    @Override
    public void deletePeople(String peopleId) throws ServiceException {
        Person operator = getLoginPeople();
        Person target = queryById(peopleId);
        LogUtils.info("{}删除人员{}操作开始", operator.getCode(), target.getCode());
        if (target.getCode().equals(operator.getCode())) {
            LogUtils.error("{}删除自己，失败", operator.getCode());
            throw new ServiceException(DEL_FAIL_SELF);
        }
        delete(peopleId);
        peopleRoleRelRepository.deleteByPeopleId(peopleId);
        LogUtils.info("{}删除人员{}操作完成", operator.getCode(), target.getCode());
    }

    @Transactional
    @Override
    public Person getByCode(String code) throws ServiceException {
        return peopleRepository.findByCode(code);
    }

    @Override
    public long getPeopleCountByDepartment(Department department) throws ServiceException {
        return peopleRepository.countByDepartmentId(department.getIdBfDepartment());
    }

    @Override
    public List<Person> getPeopleListByDepartment(String departmentId) throws ServiceException {
        return peopleRepository.findByDepartmentId(departmentId);
    }

    @Override
    public void resetPassword(String peopleId) throws ServiceException {
        Person people = queryById(peopleId);
        Person operator = getLoginPeople();
        LogUtils.info("{}重置密码{}操作开始", operator.getCode(), people.getCode());
        people.update(operator.getCode());
        people.setPassword(StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd")));
        people.setErrorCount(COUNTER_ZERO);
        update(people);
        LogUtils.info("{}重置密码{}操作结束", operator.getCode(), people.getCode());
    }

    @Override
    public void unlock(String peopleId) throws ServiceException {
        Person people = queryById(peopleId);
        Person operator = getLoginPeople();
        LogUtils.info("{}解锁人员{}操作开始", operator.getCode(), people.getCode());
        if (!Person.STATUS_LOCKED.equals(people.getStatus())) {
            LogUtils.error("{}解锁人员{}操作失败, 人员状态为{}", operator.getCode(), people.getCode(), people.getStatus());
            throw new ServiceException(UNLOCK_FAIL_STATUS_ERR);
        }
        people.update(operator.getCode());
        people.setStatus(Person.STATUS_NORMAL);
        people.setErrorCount(COUNTER_ZERO);
        update(people);
        LogUtils.info("{}解锁人员{}操作结束", operator.getCode(), people.getCode());
    }

    @Override
    public void modifyPassword(Person people, String oldPwd, String newPwd) throws ServiceException {
        LogUtils.info("{}修改密码操作开始", people.getCode());
        if (!oldPwd.equals(people.getPassword())) {
            throw new ServiceException(UPDATE_FAIL_PWD_ERR);
        }
        people.setPassword(StringUtils.encryptMD5(people.getCode() + StringUtils.encryptMD5(newPwd)));
        update(people);
        LogUtils.info("{}修改密码操作结束", people.getCode());
    }

    @Override
    public JpaRepository<Person, String> getBaseRepository() {
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
    public void setPeopleRoleRelRepository(PersonRoleRelRepository peopleRoleRelRepository) {
        this.peopleRoleRelRepository = peopleRoleRelRepository;
    }

}
