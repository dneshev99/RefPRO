package com.refpro.server.repositories;

import com.refpro.server.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String>{
}
