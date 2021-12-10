package io.turntabl.marketservice.schedulers;

import io.turntabl.marketservice.feignclients.ExchangeOneOrderBook;
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
public class FetchOrders {

   private final ExchangeOneOrderBook exchangeOneOrderBook;
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
        List<Amazon> data = exchangeOneOrderBook.getAmazonData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Amazon(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity() ,
                        "https://exchange.matraining.com")
                )
                .collect(Collectors.toList());

        amazonRepository.deleteAll();

        amazonRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchAppleOrders() {

        List<Apple> data = exchangeOneOrderBook.getAppleData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Apple(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange.matraining.com")
                )
                .collect(Collectors.toList());

        appleRepository.deleteAll();

        appleRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchMicrosoftOrders() {
        List<Microsoft> data = exchangeOneOrderBook.getMicrosoftData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Microsoft(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange.matraining.com")
                )
                .collect(Collectors.toList());

        microsoftRepository.deleteAll();

        microsoftRepository.saveAll(data);
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchGoogleOrders() {
        List<Google> data = exchangeOneOrderBook.getGoogleData()
               .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
               .map(order -> new Google(
                       order.getProduct(),
                       order.getSide(),
                       order.getPrice(),
                       order.getQuantity() - order.getCumulatitiveQuantity(),
                       "https://exchange.matraining.com")
               )
               .collect(Collectors.toList());

        googleRepository.deleteAll();

        googleRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchTeslaOrders() {
        List<Tesla> data = exchangeOneOrderBook.getTeslaData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Tesla(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange.matraining.com")
                )
                .collect(Collectors.toList());

        teslaRepository.deleteAll();

        teslaRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchIBMOrders() {
        List<IBM> data = exchangeOneOrderBook.getIBMData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new IBM(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange.matraining.com")
                )
                .collect(Collectors.toList());

        ibmRepository.deleteAll();

        ibmRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchOracleOrders() {
        List<Oracle> data = exchangeOneOrderBook.getOracleData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Oracle(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange.matraining.com")
                )
                .collect(Collectors.toList());

        oracleRepository.deleteAll();

        oracleRepository.saveAll(data);

    }

    @Scheduled(cron = "*/30 * * * * *")
    public void fetchNetflixOrders() {
        List<Netflix> data = exchangeOneOrderBook.getNetflixData()
                .parallelStream()
                .filter(item -> item.getQuantity() - item.getCumulatitiveQuantity() > 0)
                .map(order -> new Netflix(
                        order.getProduct(),
                        order.getSide(),
                        order.getPrice(),
                        order.getQuantity() - order.getCumulatitiveQuantity(),
                        "https://exchange.matraining.com")
                )
                .collect(Collectors.toList());

        netflixRepository.deleteAll();

        netflixRepository.saveAll(data);

    }


}
