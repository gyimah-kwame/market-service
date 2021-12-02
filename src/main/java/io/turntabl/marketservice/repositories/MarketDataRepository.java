package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.MarketData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketDataRepository extends MongoRepository<MarketData, String> {

    List<MarketData> findByTickerAndExchangeId(String ticker, String exchangeId);

}