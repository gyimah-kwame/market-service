package io.turntabl.marketservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Primary
@Service
@Slf4j
public class RestTemplateService implements IRestService{

    @Autowired
    private RestTemplate restTemplate;

    public void toggleSubscription(String baseUrl, String callback, String method) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> entity = new HttpEntity<>(callback, headers);

        log.info(baseUrl+"/md/subscription");
        log.info(callback);

        if (method.equalsIgnoreCase("post")) {
             log.info("subscribe "+restTemplate.postForObject(baseUrl+"/md/subscription", entity, Boolean.class));
        }else {
            log.info("unsubscribe "+restTemplate.exchange(baseUrl+"/md/subscription", HttpMethod.DELETE, entity, Boolean.class));

        }

    }

}
