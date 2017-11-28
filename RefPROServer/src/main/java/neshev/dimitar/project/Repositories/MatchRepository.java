package neshev.dimitar.project.Repositories;

import neshev.dimitar.project.Models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match,String> {
}
