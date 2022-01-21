//package com.yahacode.yagami.pd;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.yahacode.yagami.BaseTest;
//import com.yahacode.yagami.base.BizfwServiceException;
//import com.yahacode.yagami.base.consts.ErrorCode;
//import com.yahacode.yagami.pd.model.Department;
//import com.yahacode.yagami.pd.service.DepartmentService;
//
//public class DepartmentServiceTest extends BaseTest {
//
//    @Autowired
//    private DepartmentService departmentService;
//
//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();
//
//    @Before
//    public void beforeTest() throws BizfwServiceException {
//        Department parentDepartment = departmentService.queryByCode("root");
//        Department department = new Department("test");
//        department.setCode("testForDel");
//        department.setName("测试删除机构");
//        department.setParentDepartmentId(parentDepartment.getIdBfDepartment());
//        departmentService.addDepartment(department);
//    }
//
//    @Test
//    public void testAddDepartment() throws BizfwServiceException {
//        Department department = new Department("test");
//        department.setCode("test");
//        department.setName("测试");
//        Department parentDepartment = departmentService.queryByCode("root");
//        department.setParentDepartmentId(parentDepartment.getIdBfDepartment());
//        departmentService.addDepartment(department);
//
//        Department dbDepartment = departmentService.queryByCode("test");
//        assertEquals("测试", dbDepartment.getName());
//    }
//
//    @Test
//    public void testModifyDepartment() throws BizfwServiceException {
//        Department department = departmentService.queryByCode("root");
//        department.setName("测试修改");
//        departmentService.modifyDepartment(department);
//
//        Department dbDepartment = departmentService.queryByCode("root");
//        assertEquals("测试修改", dbDepartment.getName());
//    }
//
//    @Test
//    public void testDeleteDepartmentHasChild() throws BizfwServiceException {
//        expectedException.expect(BizfwServiceException.class);
//        expectedException.expectMessage(ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_CHILD);
//
//        Department department = departmentService.queryByCode("root");
//        departmentService.deleteDepartment(department);
//    }
//
//    @Test
//    public void testDeleteDepartmentHasPeople() throws BizfwServiceException {
//        expectedException.expect(BizfwServiceException.class);
//        expectedException.expectMessage(ErrorCode.PeopleDept.Dept.DEL_FAIL_WITH_PEOPLE);
//
//        Department department = departmentService.queryByCode("11");
//        departmentService.deleteDepartment(department);
//    }
//
//    @Test
//    public void testDeleteDepartment() throws BizfwServiceException {
//        Department department = departmentService.queryByCode("testForDel");
//        departmentService.deleteDepartment(department);
//
//        Department dbDepartment = departmentService.queryByCode("testForDel");
//        assertNull(dbDepartment);
//    }
//
//    @Test
//    public void testGetParentDepartment() throws BizfwServiceException {
//        Department department = departmentService.queryByCode("1");
//        Department parentDepartment = departmentService.getParentDepartment(department);
//        assertEquals("root", parentDepartment.getName());
//    }
//
//    @Test
//    public void testGetChildDepartmentList() throws BizfwServiceException {
//        Department department = departmentService.queryByCode("root");
//        List<Department> list = departmentService.getChildDepartmentList(department.getIdBfDepartment());
//        assertTrue(list.size() >= 1);
//    }
//
//    @Test
//    public void testGetDepartmentTreeByDepartmentId() throws BizfwServiceException {
//        Department department = departmentService.queryByCode("1");
//        Department node = departmentService.getDepartmentTreeByDepartmentId(department.getIdBfDepartment());
//        assertEquals(node.getChildDepartmentList().size(), 2);
//    }
//}
