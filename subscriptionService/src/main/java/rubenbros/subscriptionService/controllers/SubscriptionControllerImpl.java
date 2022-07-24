package rubenbros.subscriptionService.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rubenbros.subscriptionService.database.SubscriptionRepository;
import rubenbros.subscriptionService.model.Subscription;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@RestController
public class SubscriptionControllerImpl implements SubscriptionController{
    @Autowired
    SubscriptionRepository subscriptionRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    enum Gender {
        male,
        female,
        other
    }
    private static final Logger log = LoggerFactory.getLogger(SubscriptionControllerImpl.class);

    public ResponseEntity<String> getAllSubscriptions() {
        try {
            return new ResponseEntity<>(objectMapper.writeValueAsString(subscriptionRepository.findAll()), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Uncontrolled exception", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> createSubscription(@Parameter(in = ParameterIn.DEFAULT, description = "Post the necessary fields for the API to create a new subscription", schema=@Schema())  @RequestBody Subscription body) {
        try{
            if(!isValid(body))
                return new ResponseEntity<>("Missing required parameters or badly formatted",HttpStatus.BAD_REQUEST);
            if(subscriptionRepository.findByEmail(body.getEmail()) == null)
                return new ResponseEntity<>(objectMapper.writeValueAsString(subscriptionRepository.save(body)), HttpStatus.OK);
            else
                return new ResponseEntity<>("Email already subscribed",HttpStatus.CONFLICT);
        }
        catch (Exception e){
            log.error("Uncontrolled exception", e);
            return new ResponseEntity<>("Uncontrolled exception",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> getSubscription(@RequestParam("email") String email) {
        try {
            Subscription subscription = subscriptionRepository.findByEmail(email);
            if (subscription != null)
                return new ResponseEntity<>(objectMapper.writeValueAsString(subscription), HttpStatus.OK);
            else
                return new ResponseEntity<>("Email not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            log.error("Uncontrolled exception", e);
            return new ResponseEntity<>("Uncontrolled exception",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> cancelSubscription(@RequestParam String email) {
        try {
            Subscription subscription = subscriptionRepository.findByEmail(email);
            if (subscription != null) {
                subscriptionRepository.delete(subscription);
                return new ResponseEntity<>("Subscription cancelled",HttpStatus.OK);
            } else
                return new ResponseEntity<>("Email not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            log.error("Uncontrolled exception", e);
            return new ResponseEntity<>("Uncontrolled exception",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean isValid(Subscription subscription){
        if(subscription.getEmail() == null || !EmailValidator.getInstance().isValid(subscription.getEmail()) ||
           subscription.getConsent() == null || !subscription.getConsent() ||
           subscription.getBirthDate() == null || subscription.getBirthDate().after(Date.from(Instant.now())) ||
           subscription.getNewsletterId() == null ||
           (subscription.getGender() != null && !isValidGender(subscription.getGender())))
            return false;
        else
            return true;
    }

    public boolean isValidGender(String gender){
        for(Gender genderType : Gender.values())
            if(genderType.name().equals(gender))
                return true;
        return false;
    }

}
