package rubenbros.subscriptionService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Subscription {
    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("birthDate")
    private Date birthDate;

    @JsonProperty("consent")
    private Boolean consent;

    @JsonProperty("newsletterId")
    private String newsletterId;

    @JsonProperty("_id")
    @JsonIgnore
    private String _id;
}
