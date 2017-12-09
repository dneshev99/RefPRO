package com.refpro.server.repositories;

import com.refpro.server.models.MatchEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchEventRepository extends MongoRepository<MatchEvent, String>{
}
