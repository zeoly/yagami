package com.yahacode.yagami.core.controller;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.core.model.Department;
import com.yahacode.yagami.core.service.DepartmentService;
import com.yahacode.yagami.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * department controller
 *
 * @author zengyongli
 */
@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PersonService personService;

    @GetMapping("/{code}")
    public Department getByCode(@PathVariable String code) {
        Department d = departmentService.findByCode(code);
        return d;
    }

//    @PostMapping
//    public void addDepartment(@RequestBody Department department) throws ServiceException {
//        Person people = getLoginPeople();
//        department.init(people.getCode());
//        departmentService.addDepartment(department);
//    }
//
//    @PatchMapping
//    public void modifyDepartment(@RequestBody Department department) throws ServiceException {
//        Person people = getLoginPeople();
//        department.update(people.getCode());
//        departmentService.modifyDepartment(department);
//    }
//
//    @DeleteMapping(value = "{id}")
//    public void deleteDepartment(@PathVariable("id") String departmentId) throws ServiceException {
//        Person people = getLoginPeople();
//        Department department = departmentService.queryById(departmentId);
//        department.update(people.getCode());
//        departmentService.deleteDepartment(department);
//    }
//
//    @GetMapping
//    public Department getDepartmentTree() throws ServiceException {
//        Person people = getLoginPeople();
//        Department loginDepartment = departmentService.queryById(people.getDepartmentId());
//        return departmentService.getDepartmentTreeByDepartmentId(loginDepartment.getIdBfDepartment());
//    }
//
//    @GetMapping(value = "{id}/people")
//    public List<Person> getPeopleListByDepartment(@PathVariable("id") String departmentId) throws ServiceException {
//        return peopleService.getPeopleListByDepartment(departmentId);
//    }

}
