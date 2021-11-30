package io.turntabl.marketservice.services;

import io.turntabl.marketservice.requests.MarketDataRequest;
import org.springframework.stereotype.Component;

@Component
public interface MessagePublisher {

    void publish(MarketDataRequest marketDataRequest, String exchangeName);
}
