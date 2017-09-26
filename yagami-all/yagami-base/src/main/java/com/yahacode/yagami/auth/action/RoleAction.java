package com.yahacode.yagami.auth.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取所有角色列表")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Role> getRoleList() throws BizfwServiceException {
        return roleService.getAllRoleList();
    }

    @ApiOperation(value = "新增角色")
    @ApiImplicitParam(name = "role", value = "角色模型", required = true, dataTypeClass = Role.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void addRole(HttpServletRequest request, @RequestBody Role role) throws BizfwServiceException {
        People people = getLoginPeople(request);
        role.init(people.getCode());
        roleService.addRole(role);
    }

    @ApiOperation(value = "更新角色信息")
    @ApiImplicitParam(name = "role", value = "角色模型", required = true, dataTypeClass = Role.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH)
    public void modifyRole(HttpServletRequest request, @RequestBody Role role) throws BizfwServiceException {
        People people = getLoginPeople(request);
        role.update(people.getCode());
        roleService.modify(role);
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "id", value = "角色id", required = true, dataTypeClass = String.class)
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteRole(@PathVariable("id") String roleId) throws BizfwServiceException {
        roleService.deleteRole(roleId);
    }

}
