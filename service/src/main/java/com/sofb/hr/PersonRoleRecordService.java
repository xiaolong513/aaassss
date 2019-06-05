package com.sofb.hr;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRoleRecordService extends CrudRepository<PersonRoleRecord, String> {
    List<PersonRoleRecord> findByPersonId(String personId);
}
