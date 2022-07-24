package rubenbros.subscriptionService;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import rubenbros.subscriptionService.database.SubscriptionRepository;
import rubenbros.subscriptionService.database.UserRepository;
import rubenbros.subscriptionService.model.Subscription;
import rubenbros.subscriptionService.model.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository users;

    private final SubscriptionRepository subscriptions;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if(this.users.findByUsername("user").isEmpty())
            this.users.save(User.builder()
                .username("user")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER"))
                .build()
            );
        if(this.users.findByUsername("admin").isEmpty())
            this.users.save(User.builder()
                    .username("admin")
                    .password(this.passwordEncoder.encode("password"))
                    .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                    .build()
            );
        if(this.subscriptions.findByEmail("ruben.jarne.cabanero@gmail.com") == null){
            Subscription subscription = new Subscription();
            subscription.setBirthDate(Date.from(LocalDate.of(1996,1,12).atStartOfDay().toInstant(ZoneOffset.UTC)));
            subscription.setEmail("ruben.jarne.cabanero@gmail.com");
            subscription.setConsent(true);
            subscription.setGender("male");
            subscription.setFirstName("Ruben");
            subscription.setNewsletterId("1");
            this.subscriptions.save(subscription);
        }
        if(this.subscriptions.findByEmail("email1@gmail.com") == null){
            Subscription subscription = new Subscription();
            subscription.setBirthDate(Date.from(LocalDate.of(1996,1,12).atStartOfDay().toInstant(ZoneOffset.UTC)));
            subscription.setEmail("email1@gmail.com");
            subscription.setConsent(true);
            subscription.setNewsletterId("1");
            this.subscriptions.save(subscription);
        }
        if(this.subscriptions.findByEmail("email2@gmail.com") == null){
            Subscription subscription = new Subscription();
            subscription.setBirthDate(Date.from(LocalDate.of(1996,1,12).atStartOfDay().toInstant(ZoneOffset.UTC)));
            subscription.setEmail("email2@gmail.com");
            subscription.setConsent(true);
            subscription.setNewsletterId("2");
            this.subscriptions.save(subscription);
        }
        if(this.subscriptions.findByEmail("email3@gmail.com") == null){
            Subscription subscription = new Subscription();
            subscription.setBirthDate(Date.from(LocalDate.of(1996,1,12).atStartOfDay().toInstant(ZoneOffset.UTC)));
            subscription.setEmail("email3@gmail.com");
            subscription.setConsent(true);
            subscription.setNewsletterId("3");
            this.subscriptions.save(subscription);
        }
        if(this.subscriptions.findByEmail("email4@gmail.com") == null){
            Subscription subscription = new Subscription();
            subscription.setBirthDate(Date.from(LocalDate.of(1996,1,12).atStartOfDay().toInstant(ZoneOffset.UTC)));
            subscription.setEmail("email4@gmail.com");
            subscription.setConsent(true);
            subscription.setNewsletterId("1");
            this.subscriptions.save(subscription);
        }
        if(this.subscriptions.findByEmail("email5@gmail.com") == null){
            Subscription subscription = new Subscription();
            subscription.setBirthDate(Date.from(LocalDate.of(1996,1,12).atStartOfDay().toInstant(ZoneOffset.UTC)));
            subscription.setEmail("email5@gmail.com");
            subscription.setConsent(true);
            subscription.setGender("female");
            subscription.setNewsletterId("2");
            this.subscriptions.save(subscription);
        }
        if(this.subscriptions.findByEmail("email6@gmail.com") == null){
            Subscription subscription = new Subscription();
            subscription.setBirthDate(Date.from(LocalDate.of(1996,1,12).atStartOfDay().toInstant(ZoneOffset.UTC)));
            subscription.setEmail("email6@gmail.com");
            subscription.setConsent(true);
            subscription.setGender("other");
            subscription.setNewsletterId("3");
            this.subscriptions.save(subscription);
        }

        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}