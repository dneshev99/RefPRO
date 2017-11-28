package neshev.dimitar.project.Repositories;

import neshev.dimitar.project.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User findByUsernameAndPassword(String username,String password);
}
