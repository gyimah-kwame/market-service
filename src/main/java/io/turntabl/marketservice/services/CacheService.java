package io.turntabl.marketservice.services;

import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.dtos.MarketDataDto;
import org.springframework.stereotype.Component;

@Component
public interface CacheService {

    void cacheData(String key, ExchangeDto value);
}
