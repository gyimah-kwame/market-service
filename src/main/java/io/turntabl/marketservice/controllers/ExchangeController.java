package io.turntabl.marketservice.controllers;

import io.turntabl.marketservice.constants.ExchangeName;
import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.requests.MarketDataRequest;
import io.turntabl.marketservice.requests.SubscriptionRequest;
import io.turntabl.marketservice.services.ExchangeService;
import io.turntabl.marketservice.services.MessagePublisher;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
@Slf4j
@AllArgsConstructor
public class ExchangeController {

    private final ExchangeService exchangeService;

    private final MessagePublisher publisher;

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
    public void getExchangeOneMarketData(@RequestBody List<MarketDataRequest> request) {
        log.info("market data from exchange one {}", request);
        publisher.publish(request, ExchangeName.EXCHANGE_ONE.toString());

    }


    @PostMapping("/exchanges/callback_two")
    public void getExchangeTwoMarketData(@RequestBody  List<MarketDataRequest> marketDataRequest) {
        log.info("market data from exchange two {}", marketDataRequest);
        publisher.publish(marketDataRequest, ExchangeName.EXCHANGE_TWO.toString());
    }

}
