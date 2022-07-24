package rubenbros.subscriptionService.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import rubenbros.subscriptionService.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByUsername(String username);
}
