package neshev.dimitar.project.Repositories;

import neshev.dimitar.project.Models.Referee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefereeRepository extends MongoRepository<Referee, String> {
    Referee findByFirstName(String firstName);
    Referee findByLastName(String lastName);
}
