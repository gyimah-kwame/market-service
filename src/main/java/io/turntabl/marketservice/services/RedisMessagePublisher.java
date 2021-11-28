package io.turntabl.marketservice.services;

import io.turntabl.marketservice.dtos.MarketDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class RedisMessagePublisher {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private ChannelTopic topic;

    /**
     * publish market data to redis
     *
     * @param marketDataDto market data from exchange
     */
    public void publish(MarketDataDto marketDataDto) {
        template.convertAndSend(topic.getTopic(),marketDataDto);
    }
}
