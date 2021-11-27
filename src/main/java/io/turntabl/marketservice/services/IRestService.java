package io.turntabl.marketservice.services;

import org.springframework.stereotype.Component;

@Component
public interface IRestService {

   void toggleSubscription(String baseUrl, String callback, String method);
}
