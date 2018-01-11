package com.yahacode.yagami.pd.action;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.pd.model.Department;
import com.yahacode.yagami.pd.model.People;
import com.yahacode.yagami.pd.service.DepartmentService;
import com.yahacode.yagami.pd.service.PeopleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * department action
 *
 * @author zengyongli
 */
@RestController
@RequestMapping("/department")
public class DepartmentAction extends BaseAction {

    private DepartmentService departmentService;

    private PeopleService peopleService;

    @ApiOperation(value = "新增机构")
    @ApiImplicitParam(name = "department", value = "机构模型", required = true, dataTypeClass = Department.class)
    @RequestMapping(method = RequestMethod.POST)
    public void addDepartment(@RequestBody Department department) throws BizfwServiceException {
        People people = getLoginPeople();
        department.init(people.getCode());
        departmentService.addDepartment(department);
    }

    @ApiOperation(value = "修改机构信息")
    @ApiImplicitParam(name = "department", value = "机构模型", required = true, dataTypeClass = Department.class)
    @RequestMapping(method = RequestMethod.PATCH)
    public void modifyDepartment(@RequestBody Department department) throws BizfwServiceException {
        People people = getLoginPeople();
        department.update(people.getCode());
        departmentService.modifyDepartment(department);
    }

    @ApiOperation(value = "删除机构")
    @ApiImplicitParam(name = "id", value = "机构id", required = true, dataTypeClass = String.class)
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteDepartment(@PathVariable("id") String departmentId) throws BizfwServiceException {
        People people = getLoginPeople();
        Department department = departmentService.queryById(departmentId);
        department.update(people.getCode());
        departmentService.deleteDepartment(department);
    }

    @ApiOperation(value = "获取登录人员机构树")
    @RequestMapping(method = RequestMethod.GET)
    public Department getDepartmentTree() throws BizfwServiceException {
        People people = getLoginPeople();
        Department loginDepartment = departmentService.queryById(people.getDepartmentId());
        return departmentService.getDepartmentTreeByDepartmentId(loginDepartment.getIdBfDepartment());
    }

    @ApiOperation(value = "获取机构下属所有人员")
    @ApiImplicitParam(name = "id", value = "机构id", required = true, dataTypeClass = String.class)
    @RequestMapping(method = RequestMethod.GET, value = "{id}/people")
    public List<People> getPeopleListByDepartment(@PathVariable("id") String departmentId) throws
            BizfwServiceException {
        return peopleService.getPeopleListByDepartment(departmentId);
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Autowired
    public void setPeopleService(PeopleService peopleService) {
        this.peopleService = peopleService;
    }
}
