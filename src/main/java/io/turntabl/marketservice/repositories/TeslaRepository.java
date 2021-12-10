package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.tickers.Netflix;
import io.turntabl.marketservice.models.tickers.Tesla;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeslaRepository extends ElasticsearchRepository<Tesla, String> {
}
