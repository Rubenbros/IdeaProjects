package rubenbros.subscriptionService.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import rubenbros.subscriptionService.model.Subscription;

import java.util.List;

public interface SubscriptionRepository extends MongoRepository<Subscription,String> {

    List<Subscription> findAll();

    Subscription findByEmail(String email);

}
