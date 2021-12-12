package io.turntabl.marketservice.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestTemplateService implements RestService {

    @Autowired
    private RestTemplate restTemplate;

    public void toggleSubscription(String baseUrl, String callback, String method) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> entity = new HttpEntity<>(callback, headers);

        if (method.equalsIgnoreCase("post")) {

            Boolean status = restTemplate.postForObject(baseUrl+"/md/subscription", entity, Boolean.class);
            log.info("subscribe "+status);

        }else {
            Boolean status = restTemplate.exchange(baseUrl+"/md/subscription", HttpMethod.DELETE, entity, Boolean.class).getBody();
            log.info("unsubscribe "+status);
        }

    }

}
