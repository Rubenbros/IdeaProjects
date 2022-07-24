package rubenbros.publicService.scheduler;

// Importing required classes
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import rubenbros.publicService.client.EmailClient;
import rubenbros.publicService.client.SubscriptionsClient;
import rubenbros.publicService.model.Email;
import rubenbros.publicService.model.Subscription;

// Annotation
@Component
// Class
public class Scheduler {

    @Autowired
    SubscriptionsClient subscriptionsClient;

    @Autowired
    EmailClient emailClient;

    @Scheduled(fixedRate = 60000)
    public void newsletterEmail() throws IOException {
        String newsletterId="1";
        String subject=new Date(Instant.now().toEpochMilli()).toString();
        String body= "Te recuerdo que estas suscrito al newsletter "+ newsletterId;
        Email email = new Email();
        email.setBody(body);
        email.setSubject(subject);
        email.setSender("newsleter."+newsletterId+"@adidas.com");
        List<String> receivers = new ArrayList<>();
        for(Subscription subscription : subscriptionsClient.getAllSubscriptions())
            if(subscription.getNewsletterId().equals(newsletterId))
                receivers.add(subscription.getEmail());
        email.setReceivers(receivers);
        emailClient.addEmail(email);
    }

    @Scheduled(fixedRate = 30000)
    public void sendEmails() throws IOException {
        emailClient.sendEmails();
    }

}