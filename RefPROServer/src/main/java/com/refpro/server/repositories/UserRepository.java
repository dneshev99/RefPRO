package com.refpro.server.repositories;

import com.refpro.server.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
}
