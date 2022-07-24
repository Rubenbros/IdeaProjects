package rubenbros.publicService.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpDelete;
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
import rubenbros.publicService.model.Subscription;

import java.io.IOException;
import java.util.*;

@Component
public class SubscriptionsClient {

    @Value("${subscriptions.uri}")
    private String subscriptionsUri;
    @Value("${credentials.username}")
    private String username;
    @Value("${credentials.password}")
    private String password;

    ObjectMapper objectMapper = new ObjectMapper();

    public String getAuthorizationToken() throws IOException {
        JSONObject authRequestBody = new JSONObject();
        authRequestBody.put("username",username);
        authRequestBody.put("password",password);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(subscriptionsUri+"/auth/signin");
        StringEntity params = new StringEntity(authRequestBody.toString(),ContentType.APPLICATION_JSON);
        request.addHeader("content-type", "application/json");
        request.setEntity(params);
        return objectMapper.readValue(EntityUtils.toString(httpClient.execute(request).getEntity()), AuthorizeUserResponse.class).getToken();
    }

    public String createSubscription(Subscription subscription) throws IOException {

        String subscriptionBody = objectMapper.writeValueAsString(subscription);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(subscriptionsUri+"/subscription");
        StringEntity params = new StringEntity(subscriptionBody,ContentType.APPLICATION_JSON);
        request.addHeader("content-type", "application/json");
        request.addHeader("Authorization","Bearer "+getAuthorizationToken());
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            return new JSONObject(EntityUtils.toString(response.getEntity())).getString("_id");
        else
            return EntityUtils.toString(response.getEntity());
    }

    public List<Subscription> getAllSubscriptions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(subscriptionsUri+"/subscriptions");
        request.addHeader("Authorization","Bearer "+getAuthorizationToken());
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            return Arrays.asList(objectMapper.readValue(EntityUtils.toString(response.getEntity()),Subscription[].class));
        else
            return null;
    }

    public boolean cancelSubscription(String email) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpDelete request = new HttpDelete(subscriptionsUri+"/subscription?email="+email);
        request.addHeader("Authorization","Bearer "+getAuthorizationToken());
        if (httpClient.execute(request).getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            return true;
        else
            return false;
    }

    public Subscription getSubscription(String email) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(subscriptionsUri+"/subscription?email="+email);
        request.addHeader("Authorization","Bearer "+getAuthorizationToken());
        HttpResponse response = httpClient.execute(request);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            return objectMapper.readValue(EntityUtils.toString(response.getEntity()),Subscription.class);
        else
            return null;
    }}
