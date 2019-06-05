package com.sofb.hr;

import org.springframework.data.repository.CrudRepository;

public interface PersonService extends CrudRepository<Person, String> {
    Person findByUserName(String userName);
}
