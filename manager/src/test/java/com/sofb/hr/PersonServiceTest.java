package com.sofb.hr;

import com.sofb.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceTest extends BaseTest {
    @Autowired
    private PersonService personService;

    @Test
    public void sava_test() {
        Person person = Person.newInstance();
        person.setUserName("123");
        person.setPassword("123");
        person.setCreatorId("yves");

        personService.save(person);

    }

}
