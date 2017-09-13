package com.yahacode.yagami.pd.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.People;
import com.yahacode.yagami.pd.service.DepartmentService;
import com.yahacode.yagami.pd.service.PeopleService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/department")
public class DepartmentAction extends BaseAction {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private PeopleService peopleService;

	@ApiOperation(value = "新增机构")
	@ApiImplicitParam(name = "department", value = "机构模型", required = true, dataType = "Department")
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public String addDepartment(HttpServletRequest request, Department department) throws BizfwServiceException {
		People people = getLoginPeople(request);
		department.init(people.getCode());
		departmentService.addDepartment(department);
		return SUCCESS;
	}

	@ApiOperation(value = "修改机构信息")
	@ApiImplicitParam(name = "department", value = "机构模型", required = true, dataType = "Department")
	@ResponseBody
	@RequestMapping(method = RequestMethod.PATCH)
	public String modifyDepartment(HttpServletRequest request, Department department) throws BizfwServiceException {
		People people = getLoginPeople(request);
		department.update(people.getCode());
		departmentService.modifyDepartment(department);
		return SUCCESS;
	}

	@ApiOperation(value = "删除机构")
	@ApiImplicitParam(name = "id", value = "机构id", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE, value = "{id}")
	public String deleteDepartment(HttpServletRequest request, @PathVariable("id") String departmentId)
			throws BizfwServiceException {
		People people = getLoginPeople(request);
		Department department = departmentService.queryById(departmentId);
		department.update(people.getCode());
		departmentService.deleteDepartment(department);
		return SUCCESS;
	}

	@ApiOperation(value = "获取登录人员机构树")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public Department getDepartmentTree(HttpServletRequest request) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Department loginDepartment = departmentService.queryById(people.getDepartmentId());
		Department department = departmentService.getDepartmentTreeByDepartmentId(loginDepartment.getIdBfDepartment());
		return department;
	}

	@ApiOperation(value = "获取机构下属所有人员")
	@ApiImplicitParam(name = "id", value = "机构id", required = true, dataType = "String")
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "{id}/people")
	public List<People> getPeopleListByDepartment(@PathVariable("id") String departmentId)
			throws BizfwServiceException {
		List<People> list = peopleService.getPeopleListByDepartment(departmentId);
		return list;
	}
}
