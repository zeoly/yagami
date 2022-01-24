//package com.yahacode.yagami.core.controller;
//
//import com.yahacode.yagami.core.model.Role;
//import com.yahacode.yagami.core.service.RoleService;
//import com.yahacode.yagami.base.BaseController;
//import com.yahacode.yagami.base.ServiceException;
//import com.yahacode.yagami.core.model.Person;
//import com.yahacode.yagami.core.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * people action
// *
// * @author zengyongli
// */
//@RestController
//@RequestMapping("/people")
//public class PeopleAction extends BaseController {
//
//    private PersonService peopleService;
//
//    private RoleService roleService;
//
//    @PostMapping
//    public void addPeople(@RequestBody Person people) throws ServiceException {
//        peopleService.addPeople(people);
//    }
//
//    @PatchMapping
//    public void modifyPeople(@RequestBody Person people) throws ServiceException {
//        peopleService.modifyPeople(people);
//    }
//
//    @DeleteMapping(value = "{id}")
//    public void deletePeople(@PathVariable("id") String peopleId) throws ServiceException {
//        peopleService.deletePeople(peopleId);
//    }
//
//    @PutMapping(value = "{id}/unlock")
//    public void unlockPeople(@PathVariable("id") String peopleId) throws ServiceException {
//        peopleService.unlock(peopleId);
//    }
//
//    @PatchMapping(value = "/password/{old}/{new}")
//    public void modifyPassword(@PathVariable("old") String oldPassword, @PathVariable("new") String newPassword)
//            throws ServiceException {
//        peopleService.modifyPassword(getLoginPeople(), oldPassword, newPassword);
//    }
//
//    @GetMapping(value = "/{id}/role")
//    public List<Role> getRoleOfPeople(@PathVariable("id") String peopleId) throws ServiceException {
//        return roleService.getRoleListByPeople(peopleId);
//    }
//
//    @Autowired
//    public void setPeopleService(PersonService peopleService) {
//        this.peopleService = peopleService;
//    }
//
//    @Autowired
//    public void setRoleService(RoleService roleService) {
//        this.roleService = roleService;
//    }
//}
