package com.yahacode.yagami.auth.service;

import com.yahacode.yagami.BaseTest;
import com.yahacode.yagami.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceTest extends BaseTest {

    @Autowired
    private RoleService roleService;

//    @Before
//    public void beforeTest() {
//        Person loginPeople = new Person();
//        loginPeople.setCode("unitTest");
//        loginPeople.setName("单元测试");
//        ServletContextHolder.getSession().setAttribute(SessionKeyConsts.LOGIN_PEOPLE, loginPeople);
//    }

//    @Test
//    public void getAllRoleList() throws Exception {
//        List<Role> roles = roleService.getAllRoleList();
//        Assert.assertTrue(roles.size() > 0);
//    }

//    @Test
//    public void addRole() throws Exception {
//        Role role = new Role("test", "测试角色", "测试角色描述");
//        roleService.addRole(role);
//
//        Role newRole = roleService.getByName("测试角色");
//        Assert.assertEquals(newRole.getDescription(), "测试角色描述");
//    }
//
//    @Test
//    public void modify() throws Exception {
//        Role role = roleService.getByName("测试角色");
//        role.setDescription("测试角色描述mod");
//        roleService.update(role);
//
//        Role newRole = roleService.getByName("测试角色");
//        Assert.assertEquals(newRole.getDescription(), "测试角色描述mod");
//    }
//
//    @Test
//    public void deleteRole() throws Exception {
//        Role role = roleService.getByName("测试角色");
//        roleService.deleteRole(role.getIdBfRole());
//
//        role = roleService.getByName("测试角色");
//        Assert.assertNull(role);
//    }
//
//    @Test
//    public void getRoleListByPeople() throws Exception {
//    }
//
//    @Test
//    public void setRoleOfPeople() throws Exception {
//    }
//
//    @Test
//    public void countPeopleByRole() throws Exception {
//    }
//
//    @Test
//    public void getByName() throws Exception {
//    }
}