package com.yahacode.yagami.core.controller;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.core.model.Department;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.service.DepartmentService;
import com.yahacode.yagami.core.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return departmentService.findByCode(code);
    }

    @GetMapping
    public List<Department> getByParentCode(@RequestParam String parentCode) {
        return departmentService.findByParentCode(parentCode);
    }

    @PostMapping
    public void addDepartment(@RequestBody Department department) throws ServiceException {
        departmentService.addDepartment(department);
    }

    @PatchMapping
    public void modifyDepartment(@RequestBody Department department) throws ServiceException {
        departmentService.modifyDepartment(department);
    }

    @DeleteMapping(value = "/{code}")
    public void deleteDepartment(@PathVariable String code) throws ServiceException {
        departmentService.deleteDepartment(code);
    }

    @GetMapping("/{code}/person")
    public List<Person> getPersonsOfDepartment(@PathVariable String code) {
        return personService.findByDepartment(code);
    }
}
