package com.yahacode.yagami.pd.service.impl;

import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.Person;
import com.yahacode.yagami.pd.repository.DepartmentRelationRepository;
import com.yahacode.yagami.pd.repository.DepartmentRepository;
import com.yahacode.yagami.pd.service.DepartmentService;
import com.yahacode.yagami.pd.service.PeopleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private PeopleService peopleService;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentRelationRepository departmentRelationRepository;

    @Override
    public Department findByCode(String code) {
        return departmentRepository.findByCode(code);
    }

    @Transactional
    @Override
    public void addDepartment(Department department) throws ServiceException {
        Person person = getLoginPerson();
        log.info("{} add department {} start", person.getCode(), department.getCode());
        Department parentDept = findByCode(department.getParentCode());
        department.setLevel(parentDept.getLevel() + 1);
        initAndSave(department);
        log.info("{} add department {} end", person.getCode(), department.getCode());
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
        checkCanDelete(department);
        deleteById(department.getId());
        log.info("{} delete department {} end", person.getCode(), code);
    }

    @Override
    public List<Department> findChildren(String code) {
        return departmentRepository.findByParentCode(code);
    }

    @Override
    public boolean hasChild(String code) {
        long count = departmentRepository.countByParentCode(code);
        return count > 0;
    }

    @Override
    public boolean hasPerson(String code) throws ServiceException {
        long count = peopleService.getPeopleCountByDepartment(department);
        return count > 0;
    }

    /**
     * 检查机构是否可删除
     *
     * @param department 机构
     * @throws ServiceException 业务异常
     */
    private void checkCanDelete(Department department) throws ServiceException {
        if (hasChild(department.getCode())) {
            log.warn("delete department fail with children, {}", department.getCode());
            throw new ServiceException(DEL_FAIL_WITH_CHILD);
        }
        boolean hasPeople = hasPeople(department);
        if (hasPeople) {
            LogUtils.error("{}删除机构{}操作失败，存在人员", department.getUpdateBy(), department.getCode());
            throw new ServiceException(DEL_FAIL_WITH_PEOPLE);
        }
    }

    @Override
    public JpaRepository<Department, String> getBaseRepository() {
        return departmentRepository;
    }

}
