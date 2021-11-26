package io.turntabl.marketservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService implements IRestService{

    @Autowired
    private RestTemplate restTemplate;

    public void toggleSubscription(String baseUrl, String callback, String method) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> entity = new HttpEntity<>(callback, headers);

        if (method.equalsIgnoreCase("post")) {
             restTemplate.postForObject(baseUrl+"/md/subscription", entity, Boolean.class);
        }

        restTemplate.exchange(baseUrl+"/md/subscription", HttpMethod.DELETE, entity, Boolean.class);

    }

}
