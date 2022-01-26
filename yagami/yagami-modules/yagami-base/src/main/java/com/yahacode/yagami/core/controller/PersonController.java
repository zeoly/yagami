package com.yahacode.yagami.core.controller;

import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.service.PersonService;
import com.yahacode.yagami.core.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * people action
 *
 * @author zengyongli
 */
@RestController
@RequestMapping("/person")
public class PersonController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/{code}")
    public Person getByCode(@PathVariable String code) {
        Person person = personService.findByCode(code);
        return person;
    }

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

}
