package com.yahacode.yagami.auth.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;

@Controller
@RequestMapping("/role")
public class RoleAction extends BaseAction {

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Role> getRoleList() throws BizfwServiceException {
		return roleService.getAllRoleList();
	}

	@ResponseBody
	@RequestMapping("/addRole.do")
	public void addRole(HttpServletRequest request, Role role) throws BizfwServiceException {
		People people = getLoginPeople(request);
		role.init(people.getCode());
		roleService.addRole(role);
	}

	@ResponseBody
	@RequestMapping("/modifyRole.do")
	public void modifyRole(HttpServletRequest request, Role role) throws BizfwServiceException {
		People people = getLoginPeople(request);
		role.update(people.getCode());
		roleService.modify(role);
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void deleteRole(@PathVariable("id") String roleId) throws BizfwServiceException {
		roleService.deleteRole(roleId);
	}

	@ResponseBody
	@RequestMapping("/getRoleOfPeople.do")
	public List<Role> getRoleOfPeople(HttpServletRequest request, String peopleId) throws BizfwServiceException {
		List<Role> roleList = roleService.getRoleListByPeople(peopleId);
		return roleList;
	}
}
