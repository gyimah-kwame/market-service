package io.turntabl.marketservice.feignclients;

import io.turntabl.marketservice.responses.OrderBookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "exchangeone", url = "https://exchange.matraining.com")
public interface ExchangeOneOrderBook {

    @GetMapping("/orderbook")
    List<OrderBookResponse> getData();
}
