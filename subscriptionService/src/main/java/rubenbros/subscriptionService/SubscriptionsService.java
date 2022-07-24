package rubenbros.subscriptionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@ConfigurationPropertiesScan
public class SubscriptionsService {

    public static void main(String[] args) {
        SpringApplication.run(SubscriptionsService.class, args);
    }
}
