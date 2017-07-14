package com.yahacode.yagami.pd.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.People;
import com.yahacode.yagami.pd.service.DepartmentService;

@Controller
@RequestMapping("/departmentAction")
public class DepartmentAction extends BaseAction {

	@Autowired
	private DepartmentService departmentService;

	@ResponseBody
	@RequestMapping("/addDepartment.do")
	public String addDepartment(HttpServletRequest request, Department department) throws BizfwServiceException {
		People people = getLoginPeople(request);
		department.init(people.getCode());
		departmentService.addDepartment(department);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/modifyDepartment.do")
	public String modifyDepartment(HttpServletRequest request, Department department) throws BizfwServiceException {
		People people = getLoginPeople(request);
		department.update(people.getCode());
		departmentService.modifyDepartment(department);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/deleteDepartment.do")
	public String deleteDepartment(HttpServletRequest request, String departmentId) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Department department = departmentService.queryById(departmentId);
		department.update(people.getCode());
		departmentService.deleteDepartment(department);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getDepartmentTree.do")
	public Department getDepartmentTree(HttpServletRequest request) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Department loginDepartment = departmentService.queryById(people.getDepartmentId());
		Department department = departmentService.getDepartmentTreeByDepartmentId(loginDepartment.getIdBfDepartment());
		return department;
	}

}
