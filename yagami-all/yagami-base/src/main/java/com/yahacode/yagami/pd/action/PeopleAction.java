package com.yahacode.yagami.pd.action;

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
import com.yahacode.yagami.pd.model.PeopleForm;
import com.yahacode.yagami.pd.service.PeopleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 人员管理action
 * 
 * @author zengyongli
 * @date 2016年3月18日
 */
@Controller
@RequestMapping("/people")
public class PeopleAction extends BaseAction {

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping("/addPeople.do")
	public void addPeople(HttpServletRequest request, @RequestBody PeopleForm peopleForm)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleForm.getPeople();
		people.init(loginPeople.getCode());
		peopleService.addPeople(people);
		roleService.setRoleOfPeople(people, peopleForm.getRoleIdList());
	}

	@ResponseBody
	@RequestMapping("/modifyPeople.do")
	public void modifyPeople(HttpServletRequest request, @RequestBody PeopleForm peopleForm)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleForm.getPeople();
		people.update(loginPeople.getCode());
		peopleService.modifyPeople(people);
		roleService.setRoleOfPeople(people, peopleForm.getRoleIdList());
	}

	@ResponseBody
	@RequestMapping("/deletePeople.do")
	public void deletePeople(HttpServletRequest request, String peopleId) throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleService.queryById(peopleId);
		people.update(loginPeople.getCode());
		peopleService.deletePeople(people);
	}

	@ResponseBody
	@RequestMapping("/unlockPeople.do")
	public void unlockPeople(HttpServletRequest request, String peopleId) throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleService.queryById(peopleId);
		people.update(loginPeople.getCode());
		peopleService.unlock(people);
	}

	@ResponseBody
	@RequestMapping("/getPeopleListByDepartment.do")
	public List<People> getPeopleListByDepartment(String departmentId) throws BizfwServiceException {
		List<People> list = peopleService.getPeopleListByDepartment(departmentId);
		return list;
	}

	@ResponseBody
	@RequestMapping("/modifyPassword.do")
	public void modifyPassword(HttpServletRequest request, String oldPassword, String newPassword)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		loginPeople.update();
		peopleService.modifyPassword(loginPeople, oldPassword, newPassword);
	}

	@ApiOperation(value = "获取人员所有角色")
	@ApiImplicitParam(name = "id", value = "人员id", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/role")
	public List<Role> getRoleOfPeople(HttpServletRequest request, @PathVariable("id") String peopleId)
			throws BizfwServiceException {
		List<Role> roleList = roleService.getRoleListByPeople(peopleId);
		return roleList;
	}
}
