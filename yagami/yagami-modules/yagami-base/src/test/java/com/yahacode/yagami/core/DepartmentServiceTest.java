package com.yahacode.yagami.core;

import com.yahacode.yagami.BaseTest;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.core.model.Department;
import com.yahacode.yagami.core.service.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentServiceTest extends BaseTest {

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void testFindByCode() {
        Department department = departmentService.findByCode("root");
        Assertions.assertEquals(department.getName(), "root");
    }

    @Test
    public void testFindByParentCode() {
        List<Department> children = departmentService.findByParentCode("root");
        Assertions.assertEquals(children.size(), 3);
    }

    @Test
    public void testAdd() throws ServiceException {
        beforeMethod();
        Department department = new Department();
        department.setCode("unit-test-dept");
        department.setName("unit-test-dept");
        department.setParentCode("root");
        departmentService.addDepartment(department);

        Department dbDepartment = departmentService.findByCode("unit-test-dept");
        Assertions.assertNotNull(dbDepartment);
    }

    @Test
    public void testModifyDepartment() throws ServiceException {
        beforeMethod();
        Department department = departmentService.findByCode("unit-test-dept");
        department.setName("unit-test-dept-2");
        departmentService.modifyDepartment(department);

        Department dbDepartment = departmentService.findByCode("unit-test-dept");
        Assertions.assertEquals(dbDepartment.getName(), "unit-test-dept-2");
    }

    @Test
    public void testDeleteDepartmentHasChild() {
        beforeMethod();
        ServiceException exception = Assertions.assertThrows(ServiceException.class, () -> {
            departmentService.deleteDepartment("root");
        });
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_CHILD);
    }

    @Test
    public void testDeleteDepartmentHasPeople() {
        beforeMethod();
        ServiceException exception = Assertions.assertThrows(ServiceException.class, () -> {
            departmentService.deleteDepartment("121");
        });
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_PEOPLE);
    }

    @Test
    public void testDeleteDepartment() throws ServiceException {
        beforeMethod();
        departmentService.deleteDepartment("unit-test-dept");

        Department db = departmentService.findByCode("unit-test-dept");
        Assertions.assertNull(db);
    }

}
