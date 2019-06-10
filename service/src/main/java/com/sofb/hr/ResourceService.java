package com.sofb.hr;

import org.springframework.data.repository.CrudRepository;

public interface ResourceService extends CrudRepository<Role, String> {
}
