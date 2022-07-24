package rubenbros.emailService.controllers;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rubenbros.emailService.model.Email;
import rubenbros.emailService.service.EmailSendService;

import java.util.ArrayList;
import java.util.List;


@RestController
public class EmailControllerImpl extends EmailController {

    @Autowired
    EmailSendService emailSendService;

    private static final Logger log = LoggerFactory.getLogger(EmailControllerImpl.class);

    List<Email> emailsToSend =  new ArrayList<>();
    @Override
    public ResponseEntity<String> addEmailToQueue(@Parameter(in = ParameterIn.DEFAULT, description = "Post the necessary fields of an email", schema=@Schema()) @RequestBody Email emailBody) {
        try {
            if (emailBody.isValid()) {
                emailsToSend.add(emailBody);
                return new ResponseEntity<>("Email added succesfully",HttpStatus.OK);
            } else
                return new ResponseEntity<>("Badly constructed email", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("Uncontrolled exception", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> sendEmails() {
        try{
            for(Email email : emailsToSend)
                for(String receiver : email.getReceivers())
                    emailSendService.sendEmail(email.getSender(),receiver,email.getSubject(),email.getBody());
                emailsToSend.clear();
                return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error("Uncontrolled exception", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
