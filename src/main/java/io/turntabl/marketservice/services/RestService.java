package io.turntabl.marketservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    private RestTemplate restTemplate;

    public Boolean subscribeExchange(String baseUrl, String callback) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> entity = new HttpEntity<>(callback, headers);

        return restTemplate.postForObject(baseUrl+"md/subscription", entity, Boolean.class);


    }

    public void unSubscribeExchange(String baseUrl) {

    }
}
