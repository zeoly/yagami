//package com.yahacode.yagami.core.controller;
//
//import com.yahacode.yagami.base.BaseController;
//import com.yahacode.yagami.base.ServiceException;
//import com.yahacode.yagami.core.model.Department;
//import com.yahacode.yagami.core.model.Person;
//import com.yahacode.yagami.core.service.DepartmentService;
//import com.yahacode.yagami.core.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * department action
// *
// * @author zengyongli
// */
//@RestController
//@RequestMapping("/department")
//public class DepartmentAction extends BaseController {
//
//    private DepartmentService departmentService;
//
//    private PersonService peopleService;
//
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
//
//    @Autowired
//    public void setDepartmentService(DepartmentService departmentService) {
//        this.departmentService = departmentService;
//    }
//
//    @Autowired
//    public void setPeopleService(PersonService peopleService) {
//        this.peopleService = peopleService;
//    }
//}
