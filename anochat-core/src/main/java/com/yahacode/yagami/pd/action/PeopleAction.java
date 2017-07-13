package com.yahacode.yagami.pd.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.People;
import com.yahacode.yagami.pd.model.PeopleForm;
import com.yahacode.yagami.pd.service.PeopleService;

/**
 * 人员管理action
 * 
 * @author zengyongli
 * @date 2016年3月18日
 */
@Controller
@RequestMapping("/peopleAction")
public class PeopleAction extends BaseAction {

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping("/addPeople.do")
	public String addPeople(HttpServletRequest request, @RequestBody PeopleForm peopleForm)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleForm.getPeople();
		people.init(loginPeople.getCode());
		peopleService.addPeople(people);
		roleService.setRoleOfPeople(people, peopleForm.getRoleIdList());
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/modifyPeople.do")
	public String modifyPeople(HttpServletRequest request, @RequestBody PeopleForm peopleForm)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleForm.getPeople();
		people.update(loginPeople.getCode());
		peopleService.modifyPeople(people);
		roleService.setRoleOfPeople(people, peopleForm.getRoleIdList());
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/deletePeople.do")
	public String deletePeople(HttpServletRequest request, String peopleId) throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleService.queryById(peopleId);
		people.update(loginPeople.getCode());
		peopleService.deletePeople(people);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/unlockPeople.do")
	public String unlockPeople(HttpServletRequest request, String peopleId) throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		People people = peopleService.queryById(peopleId);
		people.update(loginPeople.getCode());
		peopleService.unlock(people);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getPeopleListByDepartment.do")
	public List<People> getPeopleListByDepartment(String departmentId) throws BizfwServiceException {
		List<People> list = peopleService.getPeopleListByDepartment(departmentId);
		return list;
	}

	@ResponseBody
	@RequestMapping("/modifyPassword.do")
	public String modifyPassword(HttpServletRequest request, String oldPassword, String newPassword)
			throws BizfwServiceException {
		People loginPeople = getLoginPeople(request);
		loginPeople.update();
		peopleService.modifyPassword(loginPeople, oldPassword, newPassword);
		return SUCCESS;
	}
}
