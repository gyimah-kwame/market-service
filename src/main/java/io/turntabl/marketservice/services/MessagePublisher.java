package io.turntabl.marketservice.services;

import io.turntabl.marketservice.requests.MarketDataRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessagePublisher {

    void publish(List<MarketDataRequest> marketDataRequest, String exchangeName);
}
