package com.yahacode.yagami.core;

import com.yahacode.yagami.BaseTest;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RoleServiceTest extends BaseTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testFindAll() {
        List<Role> list = roleService.findAll();
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    public void testAddRole() throws ServiceException {
        Role role = new Role();
        role.setName("unitTest");
        role.setDescription("unit test");
        roleService.addRole(role);

        Role newRole = roleService.findByName("unitTest");
        Assertions.assertNotNull(newRole);
    }

    @Test
    public void testAddDuplicatedRole() {
        ServiceException e = Assertions.assertThrows(ServiceException.class, () -> {
            Role role = new Role();
            role.setName("unitTest");
            role.setDescription("unit test");
            roleService.addRole(role);
        });
        Assertions.assertEquals(e.getErrorCode(), ErrorCode.Auth.Role.ADD_FAIL_EXISTED);
    }

    @Test
    public void testDeleteRole() throws ServiceException {
        Role role = roleService.findByName("unitTest");
        roleService.deleteRole(role.getId());

        Role newRole = roleService.findByName("unitTest");
        Assertions.assertNull(newRole);
    }

    @Test
    @Transactional
    public void testDeleteRoleFail() {
        ServiceException e = Assertions.assertThrows(ServiceException.class, () -> {
            Role role = roleService.findByName("sdfdsfddf");
            int personCount = role.getPersonList().size();
            Assertions.assertTrue(personCount > 0);
            roleService.deleteRole(role.getId());
        });
        Assertions.assertEquals(e.getErrorCode(), ErrorCode.Auth.Role.DEL_FAIL_WITH_PEOPLE);
    }

}
