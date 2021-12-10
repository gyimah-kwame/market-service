package io.turntabl.marketservice.feignclients;

import io.turntabl.marketservice.responses.FullOrderBook;
import io.turntabl.marketservice.responses.OrderBookResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "exchangetwo", url = "https://exchange2.matraining.com")
public interface ExchangeTwoOrderBook {

    @GetMapping("/orderbook/AMZN/open")
    List<FullOrderBook> getAmazonData();

    @GetMapping("/orderbook/MSFT/open")
    List<FullOrderBook> getMicrosoftData();

    @GetMapping("/orderbook/GOOGL/open")
    List<FullOrderBook> getGoogleData();

    @GetMapping("/orderbook/AAPL/open")
    List<FullOrderBook> getAppleData();

    @GetMapping("/orderbook/TSLA/open")
    List<FullOrderBook> getTeslaData();

    @GetMapping("/orderbook/IBM/open")
    List<FullOrderBook> getIBMData();

    @GetMapping("/orderbook/ORCL/open")
    List<FullOrderBook> getOracleData();

    @GetMapping("/orderbook/NFLX/open")
    List<FullOrderBook> getNetflixData();
}
