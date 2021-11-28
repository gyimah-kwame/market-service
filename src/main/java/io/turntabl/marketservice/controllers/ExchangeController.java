package io.turntabl.marketservice.controllers;

import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.dtos.MarketDataDto;
import io.turntabl.marketservice.requests.SubscriptionRequest;
import io.turntabl.marketservice.services.ExchangeService;
import io.turntabl.marketservice.services.RedisMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@Slf4j
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private RedisMessagePublisher publisher;

    @GetMapping("/exchanges")
    public List<ExchangeDto> getExchanges(@RequestParam Map<String, String> filters) {
        return exchangeService.getResources();
    }

    @PostMapping("/exchanges/subscribe")
    public ExchangeDto subscribeToExchange(@Valid @RequestBody SubscriptionRequest request) {
        return exchangeService.subscribeToExchange(request.getExchangeId());
    }

    @DeleteMapping("/exchanges/unsubscribe")
    public ExchangeDto unsubscribeToExchange(@Valid @RequestBody SubscriptionRequest request) {
        return exchangeService.unsubscribeToExchange(request.getExchangeId());
    }

    @PostMapping("/exchanges/callback_one")
    public void getExchangeOneMarketData(@RequestBody MarketDataDto dto) {
        log.info("market data from exchange one {}", dto);
        publisher.publish(dto);

    }


    @PostMapping("/exchanges/callback_two")
    public void getExchangeTwoMarketData(@RequestBody MarketDataDto dto) {
        log.info("market data from exchange two {}", dto);
        publisher.publish(dto);
    }

}
