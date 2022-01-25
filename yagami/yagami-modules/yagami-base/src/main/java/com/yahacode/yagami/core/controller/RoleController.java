package com.yahacode.yagami.core.controller;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    /**
//     * 获取所有角色列表
//     *
//     * @return 角色列表
//     */
//    @RequestMapping(method = RequestMethod.GET)
//    public List<Role> getRoleList() throws ServiceException {
//        return roleService.getAllRoleList();
//    }

    @GetMapping("/{name}")
    public Role getRole(@PathVariable String name) {
        Role role = roleService.findByName(name);
        int a = role.getPersonList().size();
        return role;
    }

//    /**
//     * 新增角色
//     *
//     * @param role 角色模型
//     * @throws ServiceException
//     */
//    @RequestMapping(method = RequestMethod.POST)
//    public void addRole(@RequestBody Role role) throws ServiceException {
//        roleService.addRole(role);
//    }
//
//    /**
//     * 更新角色
//     *
//     * @param role 角色模型
//     * @throws ServiceException
//     */
//    @RequestMapping(method = RequestMethod.PATCH)
//    public void modifyRole(@RequestBody Role role) throws ServiceException {
//        roleService.modify(role);
//    }
//
//    /**
//     * 删除角色
//     *
//     * @param roleId 角色id
//     * @throws ServiceException
//     */
//    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
//    public void deleteRole(@PathVariable("id") String roleId) throws ServiceException {
//        roleService.deleteRole(roleId);
//    }

}
