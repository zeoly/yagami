package com.yahacode.yagami.core.service.impl;

import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.common.PropertiesUtils;
import com.yahacode.yagami.base.common.StringUtils;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.repository.PersonRepository;
import com.yahacode.yagami.core.service.PersonService;
import com.yahacode.yagami.core.util.PersonStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.People.*;

/**
 * PersonService implementation
 *
 * @author zengyongli
 */
@Service
public class PersonServiceImpl extends BaseServiceImpl<Person> implements PersonService {

    private static final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    private static final int COUNTER_ZERO = 0;

    @Override
    public String addPeople(Person person) throws ServiceException {
        Person operator = getLoginPerson();
        log.info("{} add person {} start", operator.getCode(), person.getCode());
        Person tmpPeople = findByCode(person.getCode());
        if (tmpPeople != null) {
            log.warn("add person fail: {} already exists", person.getCode());
            throw new ServiceException(ADD_FAIL_EXISTED, person.getCode());
        }
        return initAndSave(person);
    }

    @Override
    public void modifyPerson(Person person) throws ServiceException {
        Person operator = getLoginPerson();
        log.info("{} modify person {} start", operator.getCode(), person.getCode());
        updateById(person);
        log.info("{} modify person {} end", operator.getCode(), person.getCode());
    }

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
        log.info("{} delete person {} end", operator.getCode(), target.getCode());
    }

    @Override
    public Person findByCode(String code) {
        Person p = personRepository.findByCode(code);
        log.info("111");
        log.info(p.getPassword());
        return p;
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

}
