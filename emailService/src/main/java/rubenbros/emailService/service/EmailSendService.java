package rubenbros.emailService.service;

import org.springframework.stereotype.Component;

@Component
public class EmailSendService {

    public void sendEmail(String sender,String receiver,String subject,String body){
        System.out.println(sender+"->"+receiver+"<<"+subject+">>:"+body);
    }
}
