package rubenbros.emailService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.validator.routines.EmailValidator;

import java.time.LocalDate;
import java.util.List;

@Data
public class Email {
    @JsonProperty("sender")
    private String sender;

    @JsonProperty("receivers")
    private List<String> receivers;

    @JsonProperty("subject")
    private String subject;

    @JsonProperty("body")
    private String body;

    public boolean isValid(){
        if(sender != null && !EmailValidator.getInstance().isValid(sender))
            return false;
        for(String receiver : receivers)
            if(!EmailValidator.getInstance().isValid(receiver))
                return false;
        return true;
    }
}