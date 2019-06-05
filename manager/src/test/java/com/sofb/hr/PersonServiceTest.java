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
        person.setUserName("yves");
        person.setPassword("90b4745035342a8f12f8a127e1bf5704");
        person.setSalt("ed650f0ccbbbba61eeac0915f8256a56");
        person.setCreatorId("yves");

        personService.save(person);

    }

}
