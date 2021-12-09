package io.turntabl.marketservice.services.impl;

import com.google.gson.Gson;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.models.MarketData;
import io.turntabl.marketservice.models.Product;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.repositories.MarketDataRepository;
import io.turntabl.marketservice.repositories.ProductRepository;
import io.turntabl.marketservice.requests.MarketDataRequest;
import io.turntabl.marketservice.services.MessagePublisher;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;


@Primary
@Service
@AllArgsConstructor
@Transactional
public class RedisMessagePublisherImpl implements MessagePublisher {

    private final StringRedisTemplate template;
    private final ChannelTopic topic;
    private final Gson gson;
    private final MarketDataRepository marketDataRepository;
    private final ExchangeRepository exchangeRepository;
    private final HashOperations<String, String, String> hashOperations;

    private final ProductRepository productRepository;


    @Override
    public void publish(List<MarketDataRequest> marketDataRequest, String exchangeName) {

        Exchange exchange = exchangeRepository.findByName(exchangeName);

        List<MarketData> marketDataList = marketDataRequest.stream()
                .map(x -> MarketData.fromRequest(x,exchange.getId()))
                .collect(Collectors.toList());

        //save data to mongo
        marketDataRepository.insert(marketDataList);

        marketDataList.forEach(marketData -> {

            String key = marketData.getTicker()+"_"+exchange.getId();

            hashOperations.put(key, key, gson.toJson(marketData));

            Product product = productRepository.findByTicker(marketData.getTicker()).orElse(new Product(marketData.getTicker()));

            productRepository.save(product);


        });

    }
}
