package io.turntabl.marketservice.feignclients;

import io.turntabl.marketservice.responses.OrderBookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "exchangetwo", url = "https://exchange2.matraining.com")
public interface ExchangeTwoOrderBook {

    @GetMapping("/orderbook")
    List<OrderBookResponse> getData();
}
