package io.turntabl.marketservice.services.impl;

import com.google.gson.Gson;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.models.MarketData;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.repositories.MarketDataRepository;
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


    @Override
    public void publish(MarketDataRequest marketDataRequest, String exchangeName) {

        Exchange exchange = exchangeRepository.findByName(exchangeName);

        MarketData marketData = new MarketData();
        marketData.setSellLimit(marketDataRequest.getSellLimit());
        marketData.setTicker(marketDataRequest.getTicker());
        marketData.setMaxPriceShift(marketDataRequest.getMaxPriceShift());
        marketData.setBuyLimit(marketDataRequest.getBuyLimit());
        marketData.setBidPrice(marketDataRequest.getBidPrice());
        marketData.setAskPrice(marketDataRequest.getAskPrice());
        marketData.setLastTradedPrice(marketDataRequest.getLastTradedPrice());

        marketData.setExchangeId(exchange.getId());

        //save data to mongo
        marketDataRepository.insert(marketData);

        List<MarketData> data = marketDataRepository.findByTickerAndExchangeId(marketData.getTicker(), exchange.getId());

        OptionalDouble buyLimitOptional = data.stream().map(MarketData::getBuyLimit).mapToDouble(Double::doubleValue).average();
        OptionalDouble sellLimitOptional = data.stream().map(MarketData::getSellLimit).mapToDouble(Double::doubleValue).average();

        double buyLimit = buyLimitOptional.orElse(0);
        
        double sellLimit = sellLimitOptional.orElse(0);

        //save to redis
        String key = marketData.getTicker()+"_"+exchange.getId();

        marketData.setSellLimit(sellLimit);

        marketData.setBuyLimit(buyLimit);

        hashOperations.put(key, key, gson.toJson(marketData));

    }
}
