package com.yahacode.yagami.auth.action;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理
 *
 * @author zengyongli
 */
@RestController
@RequestMapping("/role")
public class RoleAction extends BaseAction {

    private RoleService roleService;

    /**
     * 获取所有角色列表
     *
     * @return 角色列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> getRoleList() throws BizfwServiceException {
        return roleService.getAllRoleList();
    }

    /**
     * 新增角色
     *
     * @param role 角色模型
     * @throws BizfwServiceException
     */
    @RequestMapping(method = RequestMethod.POST)
    public void addRole(@RequestBody Role role) throws BizfwServiceException {
        roleService.addRole(role);
    }

    /**
     * 更新角色
     *
     * @param role 角色模型
     * @throws BizfwServiceException
     */
    @RequestMapping(method = RequestMethod.PATCH)
    public void modifyRole(@RequestBody Role role) throws BizfwServiceException {
        roleService.modify(role);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @throws BizfwServiceException
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteRole(@PathVariable("id") String roleId) throws BizfwServiceException {
        roleService.deleteRole(roleId);
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
