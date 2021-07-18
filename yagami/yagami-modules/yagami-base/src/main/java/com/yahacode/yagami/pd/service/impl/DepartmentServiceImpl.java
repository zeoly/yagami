package com.yahacode.yagami.pd.service.impl;

import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.common.ListUtils;
import com.yahacode.yagami.base.common.LogUtils;
import com.yahacode.yagami.base.consts.SystemConsts;
import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.DepartmentRelation;
import com.yahacode.yagami.pd.repository.DepartmentRelationRepository;
import com.yahacode.yagami.pd.repository.DepartmentRepository;
import com.yahacode.yagami.pd.service.DepartmentService;
import com.yahacode.yagami.pd.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private PeopleService peopleService;

    private DepartmentRepository departmentRepository;

    private DepartmentRelationRepository departmentRelationRepository;

    @Transactional
    @Override
    public void addDepartment(Department department) throws BizfwServiceException {
        LogUtils.info("{}新增机构{}操作开始", department.getUpdateBy(), department.getCode());
        Department parentDept = queryById(department.getParentDepartmentId());
        department.setLevel(parentDept.getLevel() + 1);
        save(department);
        saveDepartmentRelation(department, parentDept);
        LogUtils.info("{}新增机构{}操作结束", department.getUpdateBy(), department.getCode());
    }

    @Override
    public void modifyDepartment(Department department) throws BizfwServiceException {
        LogUtils.info("{}修改机构{}操作开始", department.getUpdateBy(), department.getCode());
        Department dbDepartment = queryById(department.getIdBfDepartment());
        dbDepartment.setName(department.getName());
        dbDepartment.setCode(department.getCode());
        dbDepartment.update(department.getUpdateBy());
        update(dbDepartment);
        LogUtils.info("{}修改机构{}操作结束", department.getUpdateBy(), department.getCode());
    }

    @Override
    public void deleteDepartment(Department department) throws BizfwServiceException {
        LogUtils.info("{}删除机构{}操作开始", department.getUpdateBy(), department.getCode());
        checkObjectNotNull(department, "机构[" + department.getIdBfDepartment() + "]", "删除机构");
        checkCanDelete(department);
        delete(department.getIdBfDepartment());
        deleteUpperDepartmentRelation(department);
        LogUtils.info("{}删除机构{}操作结束", department.getUpdateBy(), department.getCode());
    }

    @Override
    public Department queryByCode(String code) throws BizfwServiceException {
        return departmentRepository.findByCode(code);
    }

    @Override
    public Department getParentDepartment(Department department) throws BizfwServiceException {
        DepartmentRelation parentDepartmentRel =
                departmentRelationRepository.findByChildDepartmentIdAndParentLevel(department.getIdBfDepartment(),
                        department.getLevel() - 1);
        return queryById(parentDepartmentRel.getParentDepartmentId());
    }

    @Override
    public List<Department> getChildDepartmentList(String departmentId) throws BizfwServiceException {
        return departmentRepository.findByParentDepartmentId(departmentId);
    }

    @Override
    public boolean hasChildDepartment(Department department) throws BizfwServiceException {
        long count = departmentRepository.countByParentDepartmentId(department.getIdBfDepartment());
        return count > 0;
    }

    @Override
    public boolean hasPeople(Department department) throws BizfwServiceException {
        long count = peopleService.getPeopleCountByDepartment(department);
        return count > 0;
    }

    @Override
    public Department getDepartmentTreeByDepartmentId(String departmentId) throws BizfwServiceException {
        Department department = queryById(departmentId);
        List<Department> childDepartmentList = getChildDepartmentList(departmentId);
        ListUtils.sort(childDepartmentList, Department.COLUMN_CODE);
        for (Department childDepartment : childDepartmentList) {
            childDepartment = getDepartmentTreeByDepartmentId(childDepartment.getIdBfDepartment());
        }
        department.setChildDepartmentList(childDepartmentList);
        return department;
    }

    /**
     * 保存机构关联关系
     *
     * @param department
     *         机构
     * @param parentDepartment
     *         父机构
     * @throws BizfwServiceException
     *         业务异常
     */
    private void saveDepartmentRelation(Department department, Department parentDepartment) throws BizfwServiceException {
        List<Department> parentDeptList = getAllParentDeptList(parentDepartment.getIdBfDepartment());
        parentDeptList.add(parentDepartment);
        for (Department dept : parentDeptList) {
            DepartmentRelation relation =
                    DepartmentRelation.builder().childDepartmentId(department.getIdBfDepartment()).parentDepartmentId(dept.getIdBfDepartment()).parentLevel(dept.getLevel()).build();
            relation.init(SystemConsts.SYSTEM);
            departmentRelationRepository.save(relation);
        }
    }

    /**
     * 检查机构是否可删除
     *
     * @param department
     *         机构
     * @throws BizfwServiceException
     *         业务异常
     */
    private void checkCanDelete(Department department) throws BizfwServiceException {
        boolean hasChildDepartment = hasChildDepartment(department);
        if (hasChildDepartment) {
            LogUtils.error("{}删除机构{}操作失败，存在子机构", department.getUpdateBy(), department.getCode());
            throw new BizfwServiceException(DEL_FAIL_WITH_CHILD);
        }
        boolean hasPeople = hasPeople(department);
        if (hasPeople) {
            LogUtils.error("{}删除机构{}操作失败，存在人员", department.getUpdateBy(), department.getCode());
            throw new BizfwServiceException(DEL_FAIL_WITH_PEOPLE);
        }
    }

    /**
     * delete the relation with upper departement
     *
     * @param department
     *         target department
     * @throws BizfwServiceException
     *         framework exception
     */
    private void deleteUpperDepartmentRelation(Department department) throws BizfwServiceException {
        departmentRelationRepository.deleteByChildDepartmentId(department.getIdBfDepartment());
    }


    /**
     * get the list of all parent departments
     *
     * @param departmentId
     *         primary key
     * @return the list of all parent departments
     * @throws BizfwServiceException
     *         framework exception
     */
    private List<Department> getAllParentDeptList(String departmentId) throws BizfwServiceException {
        List<Department> deptList = new ArrayList<>();
        List<DepartmentRelation> relationList = departmentRelationRepository.findByChildDepartmentId(departmentId);
        for (DepartmentRelation relation : relationList) {
            Department parentDepartment = queryById(relation.getParentDepartmentId());
            deptList.add(parentDepartment);
        }
        return deptList;
    }

    @Override
    public JpaRepository<Department, String> getBaseRepository() {
        return departmentRepository;
    }

    @Autowired
    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Autowired
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Autowired
    public void setDepartmentRelationRepository(DepartmentRelationRepository departmentRelationRepository) {
        this.departmentRelationRepository = departmentRelationRepository;
    }

}
