package rubenbros.publicService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthorizeUserRequest {

    @JsonProperty("username")
    String username;

    @JsonProperty("password")
    String password;
}
