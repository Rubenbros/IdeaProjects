package rubenbros.publicService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rubenbros.publicService.client.SubscriptionsClient;
import rubenbros.publicService.model.Subscription;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
public class PublicServiceController {

    @Autowired
    SubscriptionsClient subscriptionsClient;

    @RequestMapping("/")
    public String index(Model model) {
        Subscription subscription = new Subscription();
        List<String> genders = Arrays.asList("male","female","other");
        model.addAttribute("subscription",subscription);
        model.addAttribute("genders",genders);
        return "index";
    }

    @RequestMapping("/subscription")
    public String subscription(@RequestParam String email, Model model) throws IOException {
        Subscription subscription = subscriptionsClient.getSubscription(email);
        if(subscription==null)
            return index(model);
        model.addAttribute("firstName",subscription.getFirstName());
        model.addAttribute("email",subscription.getEmail());
        model.addAttribute("newsletterId",subscription.getNewsletterId());
        model.addAttribute("gender",subscription.getGender());
        model.addAttribute("consent",subscription.getConsent());
        model.addAttribute("birthDate",subscription.getBirthDate());
        return "subscription";
    }

    @PostMapping("/cancelSubscription")
    public String cancelSubscription(@RequestParam String email, Model model) throws IOException {
        subscriptionsClient.cancelSubscription(email);
        return index(model);
    }


    @PostMapping("/subscribe")
    @ResponseBody
    public String subscribe(@ModelAttribute("subscription") Subscription subscription) throws IOException {
        return subscriptionsClient.createSubscription(subscription);
    }

}