package io.turntabl.marketservice.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Primary
@Service
@Slf4j
public class WebClientService implements IRestService{

    @Autowired
    @Qualifier("subscriptionClient")
    private WebClient webClient;

    @Override
    public void toggleSubscription(String baseUrl, String callback, String method) {
        if (method.equalsIgnoreCase("post")) {
            webClient.post()
                    .uri(baseUrl+"/md/subscription")
                    .body(Mono.just(callback), String.class)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .subscribe(x -> log.info("subscribe "+x));

        }else {

            webClient.method(HttpMethod.DELETE)
                    .uri(baseUrl+"/md/subscription")
                    .body(Mono.just(callback), String.class)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .subscribe(x -> log.info("unsubscribe "+x));

        }
    }
}
