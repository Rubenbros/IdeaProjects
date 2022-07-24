package rubenbros.publicService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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

}