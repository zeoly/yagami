package com.yahacode.yagami.core.service.impl;

import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.core.model.Department;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.repository.DepartmentRepository;
import com.yahacode.yagami.core.service.DepartmentService;
import com.yahacode.yagami.core.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_CHILD;
import static com.yahacode.yagami.base.consts.ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_PEOPLE;

/**
 * DepartmentService implementation
 *
 * @author zengyongli
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private PersonService peopleService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department findByCode(String code) {
        return departmentRepository.findByCode(code);
    }

    @Override
    public void addDepartment(Department department) throws ServiceException {
        Person person = getLoginPerson();
        log.info("{} add department [{}] start", person.getCode(), department.getCode());
        checkDepartmentCode(department.getCode());
        checkDepartmentCode(department.getParentCode());
        Department parentDept = findByCode(department.getParentCode());
        department.setLevel(parentDept.getLevel() + 1);
        initAndSave(department);
        log.info("{} add department [{}] end", person.getCode(), department.getCode());
    }

    @Override
    public void modifyDepartment(Department department) throws ServiceException {
        Person person = getLoginPerson();
        log.info("{} modify department {} start", person.getCode(), department.getCode());
        Department dbDepartment = findByCode(department.getCode());
        dbDepartment.setName(department.getName());
        updateById(dbDepartment);
        log.info("{} modify department {} end", person.getCode(), department.getCode());
    }

    @Override
    public void deleteDepartment(String code) throws ServiceException {
        Person person = getLoginPerson();
        log.info("{} delete department {} start", person.getCode(), code);
        Department department = findByCode(code);
        checkCanDelete(code);
        deleteById(department.getId());
        log.info("{} delete department {} end", person.getCode(), code);
    }

    @Override
    public List<Department> findByParentCode(String code) {
        return departmentRepository.findByParentCode(code);
    }

    @Override
    public boolean hasChild(String code) {
        long count = departmentRepository.countByParentCode(code);
        return count > 0;
    }

    @Override
    public boolean hasPerson(String code) {
        long count = peopleService.countPersonByDepartment(code);
        return count > 0;
    }

    /**
     * check whether the code exists
     *
     * @param code department code
     * @throws ServiceException if the code exists
     */
    private void checkDepartmentCode(String code) throws ServiceException {
        Department department = findByCode(code);
        if (department != null) {
            log.warn("department code [{}] duplicated", code);
            throw new ServiceException("");
        }
    }

    /**
     * check whether a department can be deleted
     *
     * @param code department code
     * @throws ServiceException with child department or contain person
     */
    private void checkCanDelete(String code) throws ServiceException {
        if (hasChild(code)) {
            log.warn("delete department fail with children, {}", code);
            throw new ServiceException(DEL_FAIL_WITH_CHILD);
        }
        if (hasPerson(code)) {
            log.warn("delete department fail with person, {}", code);
            throw new ServiceException(DEL_FAIL_WITH_PEOPLE);
        }
    }

    @Override
    public JpaRepository<Department, String> getBaseRepository() {
        return departmentRepository;
    }

}
