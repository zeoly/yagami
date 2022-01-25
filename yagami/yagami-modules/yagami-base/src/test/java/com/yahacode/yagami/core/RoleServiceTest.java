package com.yahacode.yagami.core;

import com.yahacode.yagami.BaseTest;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceTest extends BaseTest {

    @Autowired
    private RoleService roleService;

//    private PersonService peopleService;

    @Test
    public void testFindAll() {
        List<Role> list = roleService.findAll();
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    public void testAddRole() throws ServiceException {
        beforeMethod();
        Role role = new Role();
        role.setName("unitTest");
        role.setDescription("unit test");
        roleService.addRole(role);

        Role newRole = roleService.findByName("unitTest");
        Assertions.assertNotNull(newRole);
    }

    @Test
    public void testAddRepeatRole() {
        ServiceException e = Assertions.assertThrows(ServiceException.class, () -> {
            beforeMethod();
            Role role = new Role();
            role.setName("unitTest");
            role.setDescription("unit test");
            roleService.addRole(role);
        });
        Assertions.assertEquals(e.getErrorCode(), ErrorCode.Auth.Role.ADD_FAIL_EXISTED);
    }

    @Test
    public void testDeleteRole() throws ServiceException {
        beforeMethod();
        Role role = roleService.findByName("unitTest");
        roleService.deleteRole(role.getId());

        Role newRole = roleService.findByName("unitTest");
        Assertions.assertNull(newRole);
    }

}
