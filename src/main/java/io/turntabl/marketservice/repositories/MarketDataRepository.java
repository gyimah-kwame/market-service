package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.MarketData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDataRepository extends MongoRepository<MarketData, String> {
}
