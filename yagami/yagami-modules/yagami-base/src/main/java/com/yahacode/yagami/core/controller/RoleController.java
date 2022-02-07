package com.yahacode.yagami.core.controller;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * role controller
 *
 * @author zengyongli
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取所有角色列表
     *
     * @return 角色列表
     */
    @GetMapping
    public List<Role> getRoleList() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public Role getRole(@PathVariable String id) {
        return roleService.findById(id);
    }

    @PostMapping
    public void addRole(@RequestBody Role role) throws ServiceException {
        roleService.addRole(role);
    }

    @PatchMapping("/{id}")
    public void modifyRole(@PathVariable String id, @RequestBody Role role) throws ServiceException {
        role.setId(id);
        roleService.modify(role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable("id") String roleId) throws ServiceException {
        roleService.deleteRole(roleId);
    }

}
