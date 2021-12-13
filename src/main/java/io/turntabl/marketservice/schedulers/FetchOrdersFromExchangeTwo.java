package io.turntabl.marketservice.schedulers;

import io.turntabl.marketservice.feignclients.ExchangeTwoOrderBook;
import io.turntabl.marketservice.models.tickers.*;
import io.turntabl.marketservice.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
@AllArgsConstructor
public class FetchOrdersFromExchangeTwo {

   private final ExchangeTwoOrderBook exchangeTwoOrderBook;
   private final AmazonRepository amazonRepository;
   private final GoogleRepository googleRepository;
   private final MicrosoftRepository microsoftRepository;
   private final NetflixRepository netflixRepository;
   private final AppleRepository appleRepository;
   private final TeslaRepository teslaRepository;
   private final IBMRepository ibmRepository;
   private final OracleRepository oracleRepository;


    @Scheduled(cron = "*/30 * * * * *")
    public void fetchAmazonOrders() {
        List<Amazon> data = exchangeTwoOrderBook.getAmazonData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Amazon(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity() ,
                        "https://exchange2.matraining.com")
                )
                .collect(Collectors.toList());

        amazonRepository.deleteAllByExchangeURL("https://exchange2.matraining.com");

        amazonRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchAppleOrders() {

        List<Apple> data = exchangeTwoOrderBook.getAppleData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Apple(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange2.matraining.com")
                )
                .collect(Collectors.toList());

        appleRepository.deleteAllByExchangeURL("https://exchange2.matraining.com");

        appleRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchMicrosoftOrders() {
        List<Microsoft> data = exchangeTwoOrderBook.getMicrosoftData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Microsoft(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange2.matraining.com")
                )
                .collect(Collectors.toList());

        microsoftRepository.deleteAllByExchangeURL("https://exchange2.matraining.com");

        microsoftRepository.saveAll(data);
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchGoogleOrders() {
        List<Google> data = exchangeTwoOrderBook.getGoogleData()
               .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
               .map(order -> new Google(
                       order.getProduct(),
                       order.getSide(),
                       order.getPrice(),
                       order.getQuantity() - order.getCumulatitiveQuantity(),
                       "https://exchange2.matraining.com")
               )
               .collect(Collectors.toList());

        googleRepository.deleteAllByExchangeURL("https://exchange2.matraining.com");

        googleRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchTeslaOrders() {
        List<Tesla> data = exchangeTwoOrderBook.getTeslaData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Tesla(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange2.matraining.com")
                )
                .collect(Collectors.toList());

        teslaRepository.deleteAllByExchangeURL("https://exchange2.matraining.com");

        teslaRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchIBMOrders() {
        List<IBM> data = exchangeTwoOrderBook.getIBMData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new IBM(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange2.matraining.com")
                )
                .collect(Collectors.toList());

        ibmRepository.deleteAllByExchangeURL("https://exchange2.matraining.com");

        ibmRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchOracleOrders() {
        List<Oracle> data = exchangeTwoOrderBook.getOracleData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Oracle(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange2.matraining.com")
                )
                .collect(Collectors.toList());

        oracleRepository.deleteAllByExchangeURL("https://exchange2.matraining.com");

        oracleRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchNetflixOrders() {
        List<Netflix> data = exchangeTwoOrderBook.getNetflixData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Netflix(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange2.matraining.com")
                )
                .collect(Collectors.toList());

        netflixRepository.deleteAllByExchangeURL("https://exchange2.matraining.com");

        netflixRepository.saveAll(data);

    }


}
