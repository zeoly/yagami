package com.yahacode.yagami.auth;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseTest;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;
import com.yahacode.yagami.pd.service.PeopleService;

public class RoleServiceTest extends BaseTest {

    private RoleService roleService;

    private PeopleService peopleService;


    //    @Test
    @Rollback(false)
    public void test() {
        try {
            Role role = new Role("test", "测试角色", "测试角色描述");
            roleService.addRole(role);

            role = roleService.getByName("测试角色");
            System.out.println(role.getName());
            role.setName("测试角色1");
            roleService.modify(role);

            role = roleService.getByName("测试角色1");
            System.out.println(role.getName());
            roleService.deleteRole(role.getIdBfRole());
        } catch (BizfwServiceException e) {
            System.out.println(e.getErrorMsg());
        }
    }

    // @Test
    @Rollback(false)
    public void testBindRole() {
        try {
            People people = peopleService.getByCode("rrrr");
            List<String> roleList = new ArrayList<String>();
            roleList.add("8a8080875825fe41015825fe6ad40000");
            roleList.add("8a8080875825fe41015825ff02d60002");
            people.setRoleIdList(roleList);
            roleService.setRoleOfPeople(people);
            List<Role> list = roleService.getRoleListByPeople(people.getIdBfPeople());
            for (Role role : list) {
                System.out.println(role.getName());
            }
        } catch (BizfwServiceException e) {
            System.out.println(e.getErrorMsg());
        }
    }

    // @Test
    @Rollback(false)
    public void testGetRoleListByPeople() {
        try {
            People people = peopleService.getByCode("rrrr");
            List<Role> list = roleService.getRoleListByPeople(people.getIdBfPeople());
            for (Role role : list) {
                System.out.println(role.getName());
            }
        } catch (BizfwServiceException e) {
            System.out.println(e.getErrorMsg());
        }
    }

    @Test
    public void testGetRoleList() throws BizfwServiceException {
        List<Role> list = roleService.getAllRoleList();
        Assert.assertNotNull(list);
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }
}
