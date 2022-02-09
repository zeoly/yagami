package com.yahacode.yagami.core;

import com.yahacode.yagami.BaseTest;
import com.yahacode.yagami.base.ServiceException;
import com.yahacode.yagami.base.consts.ErrorCode;
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

    @Test
    public void testFindByCode() {
        Person person = personService.findByCode("admin");
        Assertions.assertNotNull(person);
    }

    @Test
    public void testFindByDepartment() {
        List<Person> list = personService.findByDepartment("121");
        Assertions.assertEquals(list.size(), 1);
    }

    @Test
    public void testModifyPersonName() throws ServiceException {
        Person person = personService.findByCode("admin");
        person.setName("testName");
        personService.modifyPerson(person);

        Person newPerson = personService.findByCode("admin");
        Assertions.assertEquals("testName", newPerson.getName());
    }

    @Test
    public void testModifyRole() throws ServiceException {
        Person person = personService.findByCode("admin");
        Role role = roleService.findByName("333");
        role.setName("444");
        person.getRoleList().add(role);
        personService.modifyPerson(person);

        Person newPerson = personService.findByCode("admin");
        Assertions.assertTrue(newPerson.getRoleList().size() > 2);
    }

    @Test
    public void testAddPerson() throws ServiceException {
        Person person = new Person();
        person.init("unit-test");
        person.setCode("unit-test");
        person.setName("unit-test");
        person.setDepartmentCode("root");

        personService.addPerson(person);
        Person db = personService.findByCode("unit-test");
        Assertions.assertNotNull(db);
    }

    @Test
    public void testAddPersonExists() {
        Person person = new Person();
        person.setCode("unit-test");

        ServiceException exception = Assertions.assertThrows(ServiceException.class, () -> personService.addPerson(person));
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.PeopleDept.People.ADD_FAIL_EXISTED);
    }

    @Test
    public void testDeletePersonBySelf() {
        ServiceException exception = Assertions.assertThrows(ServiceException.class, () -> personService.deletePerson("UNITTEST"));
        Assertions.assertEquals(exception.getErrorCode(), ErrorCode.PeopleDept.People.DEL_FAIL_SELF);
    }

    @Test
    public void testDeletePerson() throws ServiceException {
        personService.deletePerson("UNIT-TEST");

        Person db = personService.findByCode("UNIT-TEST");
        Assertions.assertNull(db);
    }
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
