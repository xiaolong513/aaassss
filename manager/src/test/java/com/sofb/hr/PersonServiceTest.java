package com.sofb.hr;

import com.sofb.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

public class PersonServiceTest extends BaseTest {
    @Autowired
    private PersonService personService;

    @Test
    public void sava_test() {
        Person person = Person.newInstance();
        person.setUserName("admin");
        person.setCreatorId("yves");
        person.setNumber("10001");
        person.setPhone("15976883030");

        personService.savePerson(person);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void changePassword_test() {
        boolean result = personService.changePassword("475d829e-2c8e-4a81-b10e-a8f126774944", "123");
        System.out.println("=================" + result);
    }

}
