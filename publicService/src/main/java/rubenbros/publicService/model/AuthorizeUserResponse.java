package rubenbros.publicService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthorizeUserResponse {

    @JsonProperty("username")
    private String username;

    @JsonProperty("token")
    private String token;
}
