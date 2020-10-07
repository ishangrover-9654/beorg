package com.ishan.rd.beorg.repository;


import com.ishan.rd.beorg.domain.entities.Role;
import com.ishan.rd.beorg.domain.entities.RoleEnum;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(RoleEnum name);
}
