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
import io.swagger.annotations.ApiImplicitParams;
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

	@ApiOperation(value = "新增人员信息")
	@ApiImplicitParam(name = "peopleForm", value = "人员表单信息", required = true, dataTypeClass = PeopleForm.class)
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public void addPeople(HttpServletRequest request, @RequestBody PeopleForm peopleForm) throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleForm.getPeople();
		people.init(loginPeople.getCode());
		peopleService.addPeople(people);
		roleService.setRoleOfPeople(people, peopleForm.getRoleIdList());
	}

	@ApiOperation(value = "修改人员信息")
	@ApiImplicitParam(name = "peopleForm", value = "人员表单信息", required = true, dataTypeClass = PeopleForm.class)
	@ResponseBody
	@RequestMapping(method = RequestMethod.PATCH)
	public void modifyPeople(HttpServletRequest request, @RequestBody PeopleForm peopleForm)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleForm.getPeople();
		people.update(loginPeople.getCode());
		peopleService.modifyPeople(people);
		roleService.setRoleOfPeople(people, peopleForm.getRoleIdList());
	}

	@ApiOperation(value = "删除人员")
	@ApiImplicitParam(name = "id", value = "人员id", required = true, dataTypeClass = String.class)
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public void deletePeople(HttpServletRequest request, @PathVariable("id") String peopleId)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleService.queryById(peopleId);
		people.update(loginPeople.getCode());
		peopleService.deletePeople(people);
	}

	@ApiOperation(value = "解锁人员")
	@ApiImplicitParam(name = "id", value = "人员id", required = true, dataTypeClass = String.class)
	@ResponseBody
	@RequestMapping(method = RequestMethod.PATCH, value = "{id}/unlock")
	public void unlockPeople(HttpServletRequest request, @PathVariable("id") String peopleId)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleService.queryById(peopleId);
		people.update(loginPeople.getCode());
		peopleService.unlock(people);
	}

	@ApiOperation(value = "修改登录用户密码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "old", value = "原密码", required = true, dataTypeClass = String.class),
			@ApiImplicitParam(name = "new", value = "新密码", required = true, dataTypeClass = String.class) })
	@ResponseBody
	@RequestMapping(method = RequestMethod.PATCH, value = "/password/{old}/{new}")
	public void modifyPassword(HttpServletRequest request, @PathVariable("old") String oldPassword,
			@PathVariable("new") String newPassword) throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		loginPeople.update();
		peopleService.modifyPassword(loginPeople, oldPassword, newPassword);
	}

	@ApiOperation(value = "获取人员所有角色")
	@ApiImplicitParam(name = "id", value = "人员id", required = true, dataTypeClass = String.class)
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/{id}/role")
	public List<Role> getRoleOfPeople(HttpServletRequest request, @PathVariable("id") String peopleId)
			throws BizfwServiceException {
		List<Role> roleList = roleService.getRoleListByPeople(peopleId);
		return roleList;
	}
}
