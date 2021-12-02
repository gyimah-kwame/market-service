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
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


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

    private final ProductRepository productRepository;


    @Override
    public void publish(MarketDataRequest marketDataRequest, String exchangeName) {

        MarketData marketData = new MarketData();
        marketData.setSellLimit(marketDataRequest.getSellLimit());
        marketData.setTicker(marketData.getTicker());
        marketData.setMaxPriceShift(marketData.getMaxPriceShift());
        marketData.setBuyLimit(marketData.getBuyLimit());
        marketData.setBidPrice(marketData.getBidPrice());
        marketData.setAskPrice(marketData.getAskPrice());
        marketData.setLastTradedPrice(marketData.getLastTradedPrice());

        Exchange exchange = exchangeRepository.findByName(exchangeName);

        marketData.setExchangeId(exchange.getId());

        //save data to mongo
        marketDataRepository.insert(marketData);

        Product product = productRepository.findByTicker(marketData.getTicker()).orElse(new Product(marketData.getTicker()));

        productRepository.insert(product);

        template.convertAndSend(topic.getTopic(),gson.toJson(marketDataRequest));
    }
}
