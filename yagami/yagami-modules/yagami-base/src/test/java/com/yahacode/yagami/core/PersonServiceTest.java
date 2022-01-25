package com.yahacode.yagami.core;

import com.yahacode.yagami.BaseTest;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.core.model.Person;
import com.yahacode.yagami.core.model.Role;
import com.yahacode.yagami.core.service.PersonService;
import com.yahacode.yagami.core.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonServiceTest extends BaseTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private RoleService roleService;

//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testFindByCode() {
        Person person = personService.findByCode("admin");
        Assertions.assertNotNull(person);
    }

    @Test
    public void testFindByDepartment() {
        List<Person> list = personService.findByDepartment("root");
        Assertions.assertTrue(list.size() > 0);
    }

    @Test
    public void testModifyPersonName() throws ServiceException {
        beforeMethod();
        Person person = personService.findByCode("admin");
        person.setName("testName");
        personService.modifyPerson(person);

        Person newPerson = personService.findByCode("admin");
        Assertions.assertEquals("testName", newPerson.getName());
    }

    @Test
    public void testModifyRole() throws ServiceException {
        beforeMethod();
        Person person = personService.findByCode("admin");
        Role role = roleService.findByName("333");
        role.setName("444");
        person.getRoleList().add(role);
        personService.modifyPerson(person);

        Person newPerson = personService.findByCode("admin");
        Assertions.assertTrue(newPerson.getRoleList().size() > 2);
    }
//    @Test
//    public void testAddPeopleExists() throws BizfwServiceException {
//        expectedException.expect(BizfwServiceException.class);
//        expectedException.expectMessage(ErrorCode.PeopleDept.People.ADD_FAIL_EXISTED);
//
//        People people = new People("test");
//        people.setCode("zengyongli");
//        people.setName("曾永理");
//        people.setDepartmentId("0");
//        peopleService.addPeople(people);
//    }
//
//    @Test
//    public void testAddPeopleDeptErr() throws BizfwServiceException {
//        expectedException.expect(BizfwServiceException.class);
//        expectedException.expectMessage(ErrorCode.PeopleDept.People.ADD_FAIL_WITHOUT_DEPT);
//
//        People people = new People("test");
//        people.setCode("testadd");
//        people.setName("testadd");
//        people.setDepartmentId("0");
//        peopleService.addPeople(people);
//    }
//
//    @Test
//    public void testAddPeople() throws BizfwServiceException {
//        People people = new People("test");
//        people.setCode("testadd");
//        people.setName("testadd");
//        people.setDepartmentId("8a808087583fa7b701583faadf300000");
//        String id = peopleService.addPeople(people);
//
//        People dbPeople = peopleService.queryById(id);
//        assertEquals(dbPeople.getCode(), "testadd");
//    }

//    @Test
//    public void testDeletePeopleDelSelf() throws BizfwServiceException {
//        expectedException.expect(BizfwServiceException.class);
//        expectedException.expectMessage(ErrorCode.PeopleDept.People.DEL_FAIL_SELF);
//
//        People people = peopleService.getByCode("zengyongli");
//        people.setUpdateBy("zengyongli");
//        peopleService.deletePeople(people.getIdBfPeople());
//    }
//
//    @Test
//    public void testDeletePeople() throws BizfwServiceException {
//        People people = peopleService.getByCode("zengyongli");
//        people.setUpdateBy("testdelete");
//        peopleService.deletePeople(people.getIdBfPeople());
//
//        People dbPeople = peopleService.getByCode("zengyongli");
//        assertEquals(dbPeople, null);
//    }
//
//    @Test
//    public void testGetPeopleListByDepartment() throws BizfwServiceException {
//        List<People> list = peopleService.getPeopleListByDepartment("8a808087583fa7b701583faadf300000");
//        assertEquals(list.size(), 3);
//    }
//
//    @Test
//    public void testUnlockFail() throws BizfwServiceException {
//        expectedException.expect(BizfwServiceException.class);
//        expectedException.expectMessage(ErrorCode.PeopleDept.People.UNLOCK_FAIL_STATUS_ERR);
//
//        People people = peopleService.getByCode("admin");
//        peopleService.unlock(people.getIdBfPeople());
//    }
//
//    @Test
//    public void testUnlock() throws BizfwServiceException {
//        People people = peopleService.getByCode("1232");
//        peopleService.unlock(people.getIdBfPeople());
//
//        assertEquals(people.getStatus(), People.STATUS_NORMAL);
//    }
//
//    @Test
//    public void testModifyPasswordFail() throws BizfwServiceException {
//        expectedException.expect(BizfwServiceException.class);
//        expectedException.expectMessage(ErrorCode.PeopleDept.People.UPDATE_FAIL_PWD_ERR);
//
//        People people = peopleService.getByCode("admin");
//        peopleService.modifyPassword(people, "666666", "777777");
//    }
//
//    @Test
//    public void testModifyPassword() throws BizfwServiceException {
//        String md5 = StringUtils.encryptMD5("777777");
//        People people = peopleService.getByCode("admin");
//        peopleService.modifyPassword(people, "888888", "777777");
//
//        People dbPeople = peopleService.getByCode("admin");
//        assertEquals(md5, dbPeople.getPassword());
//    }
}
