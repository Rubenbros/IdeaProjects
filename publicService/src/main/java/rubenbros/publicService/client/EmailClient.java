package rubenbros.publicService.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rubenbros.publicService.model.AuthorizeUserResponse;
import rubenbros.publicService.model.Email;
import rubenbros.publicService.model.Subscription;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class EmailClient {

    @Value("${email.uri}")
    private String emailUri;
    @Value("${credentials.username}")
    private String username;
    @Value("${credentials.password}")
    private String password;

    ObjectMapper objectMapper = new ObjectMapper();

    public String addEmail(Email email) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(emailUri+"/emails");
        StringEntity params = new StringEntity(objectMapper.writeValueAsString(email), ContentType.APPLICATION_JSON);
        request.addHeader("Authorization","Bearer "+getAuthorizationToken());
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        return EntityUtils.toString(httpClient.execute(request).getEntity());
    }

    public String sendEmails() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(emailUri+"/emails/send");
        request.addHeader("Authorization","Bearer "+getAuthorizationToken());
        request.addHeader("content-type", "application/json");
        return EntityUtils.toString(httpClient.execute(request).getEntity());
    }

    public String getAuthorizationToken() throws IOException {
        JSONObject authRequestBody = new JSONObject();
        authRequestBody.put("username",username);
        authRequestBody.put("password",password);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(emailUri+"/auth/signin");
        StringEntity params = new StringEntity(authRequestBody.toString(),ContentType.APPLICATION_JSON);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        return objectMapper.readValue(EntityUtils.toString(httpClient.execute(request).getEntity()), AuthorizeUserResponse.class).getToken();
    }
}
