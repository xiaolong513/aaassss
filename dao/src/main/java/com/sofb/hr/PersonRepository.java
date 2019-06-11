package com.sofb.hr;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, String> {
    Person findByUserName(String userName);
}
