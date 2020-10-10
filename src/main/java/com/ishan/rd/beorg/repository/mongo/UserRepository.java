package com.ishan.rd.beorg.repository.mongo;

import com.ishan.rd.beorg.domain.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String s);
    Optional<User> findByEmail(String email);
}
