package com.yahacode.yagami.core;

import com.yahacode.yagami.BaseTest;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

public class RoleServiceTest extends BaseTest {

    @Autowired
    private RoleService roleService;

//    private PersonService peopleService;


    //    @Test
//    @Rollback(false)
//    public void test() {
//        try {
//            Role role = new Role("test", "测试角色", "测试角色描述");
//            roleService.addRole(role);
//
//            role = roleService.getByName("测试角色");
//            System.out.println(role.getName());
//            role.setName("测试角色1");
//            roleService.modify(role);
//
//            role = roleService.getByName("测试角色1");
//            System.out.println(role.getName());
//            roleService.deleteRole(role.getIdBfRole());
//        } catch (ServiceException e) {
//            System.out.println(e.getErrorMsg());
//        }
//    }
//
//    // @Test
//    @Rollback(false)
//    public void testBindRole() {
//        try {
//            Person people = peopleService.getByCode("rrrr");
//            List<String> roleList = new ArrayList<String>();
//            roleList.add("8a8080875825fe41015825fe6ad40000");
//            roleList.add("8a8080875825fe41015825ff02d60002");
//            people.setRoleIdList(roleList);
//            roleService.setRoleOfPeople(people);
//            List<Role> list = roleService.getRoleListByPeople(people.getIdBfPeople());
//            for (Role role : list) {
//                System.out.println(role.getName());
//            }
//        } catch (ServiceException e) {
//            System.out.println(e.getErrorMsg());
//        }
//    }
//
//    // @Test
//    @Rollback(false)
//    public void testGetRoleListByPeople() {
//        try {
//            Person people = peopleService.getByCode("rrrr");
//            List<Role> list = roleService.getRoleListByPeople(people.getIdBfPeople());
//            for (Role role : list) {
//                System.out.println(role.getName());
//            }
//        } catch (ServiceException e) {
//            System.out.println(e.getErrorMsg());
//        }
//    }

    @Test
    public void testGetRoleList() throws ServiceException {
        List<Role> list = roleService.findAll();
        Assert.notNull(list, "aa");
    }

}
