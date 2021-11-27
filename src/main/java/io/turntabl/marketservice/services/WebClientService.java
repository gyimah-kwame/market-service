package io.turntabl.marketservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class WebClientService implements IRestService{

    @Autowired
    private WebClient webClient;

    @Override
    public void toggleSubscription(String baseUrl, String callback, String method) {

    }
}
