package io.turntabl.marketservice.services.impl;

import com.google.gson.Gson;
import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.dtos.MarketDataDto;
import io.turntabl.marketservice.services.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
public class RedisCacheService implements CacheService {

    private final HashOperations<String, String, String> hashOperations;
    private final Gson gson;

    @Override
    public void cacheData(String key, ExchangeDto value) {
        hashOperations.put(key, key, gson.toJson(value));
    }
}
