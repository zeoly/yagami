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
import com.yahacode.yagami.core.util.PersonStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    @Lazy
    private DepartmentService departmentService;

    private RoleService roleService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonRoleRelRepository peopleRoleRelRepository;

    private static final int COUNTER_ZERO = 0;

    @Transactional
    @Override
    public String addPeople(Person person) throws ServiceException {
        Person operator = getLoginPerson();
        log.info("{} add person {} start", operator.getCode(), person.getCode());
        Person tmpPeople = findByCode(person.getCode());
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
//        roleService.setRoleOfPeople(people);
        return id;
    }

    @Transactional
    @Override
    public void modifyPeople(Person person) throws ServiceException {
        Person operator = getLoginPerson();
        log.info("{} modify person {} start", operator.getCode(), person.getCode());
//        roleService.setRoleOfPeople(people);
        updateById(person);
        log.info("{} modify person {} end", operator.getCode(), person.getCode());
    }

    @Transactional
    @Override
    public void deletePeople(String personCode) throws ServiceException {
        Person operator = getLoginPerson();
        Person target = findByCode(personCode);
        log.info("{} delete person {} start", operator.getCode(), target.getCode());
        if (target.getCode().equals(operator.getCode())) {
            log.warn("{} delete self fail", operator.getCode());
            throw new ServiceException(DEL_FAIL_SELF);
        }
        deleteById(target.getId());
//        peopleRoleRelRepository.deleteByPeopleId(peopleId);
        log.info("{} delete person {} end", operator.getCode(), target.getCode());
    }

    @Override
    public Person findByCode(String code) {
        return personRepository.findByCode(code);
    }

    @Override
    public long countPersonByDepartment(String departmentCode) {
        return personRepository.countByDepartmentCode(departmentCode);
    }

    @Override
    public List<Person> findByDepartment(String departmentCode) {
        return personRepository.findByDepartmentCode(departmentCode);
    }

    @Override
    public void resetPassword(String personCode) throws ServiceException {
        Person person = findByCode(personCode);
        Person operator = getLoginPerson();
        log.info("{} reset password {} start", operator.getCode(), person.getCode());
        person.setPassword(StringUtils.encryptMD5(PropertiesUtils.getSysConfig("default.pwd")));
        person.setErrorCount(COUNTER_ZERO);
        updateById(person);
        log.info("{} reset password {} end", operator.getCode(), person.getCode());
    }

    @Override
    public void unlock(String personCode) throws ServiceException {
        Person person = findByCode(personCode);
        Person operator = getLoginPerson();
        log.info("{} unlock person {} start", operator.getCode(), person.getCode());
        if (PersonStatus.LOCKED != person.getStatus()) {
            log.warn("{} unlock person {} fail, status {}", operator.getCode(), person.getCode(), person.getStatus());
            throw new ServiceException(UNLOCK_FAIL_STATUS_ERR);
        }
        person.unlock();
        updateById(person);
        log.info("{} unlock person {} end", operator.getCode(), person.getCode());
    }

    @Override
    public void modifyPassword(String personCode, String oldPwd, String newPwd) throws ServiceException {
        log.info("{} modify password start", personCode);
        Person person = findByCode(personCode);
        if (!oldPwd.equals(person.getPassword())) {
            throw new ServiceException(UPDATE_FAIL_PWD_ERR);
        }
        person.setPassword(StringUtils.encryptMD5(person.getCode() + StringUtils.encryptMD5(newPwd)));
        updateById(person);
        log.info("{} modify password end", personCode);
    }

    @Override
    public JpaRepository<Person, String> getBaseRepository() {
        return personRepository;
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
