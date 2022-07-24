package rubenbros.publicService.model;

import lombok.Data;

import java.util.List;

@Data
public class AllSubscriptionsResponse {

    List<Subscription> subscriptions;
}
