package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.tickers.Netflix;
import io.turntabl.marketservice.models.tickers.Oracle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetflixRepository extends ElasticsearchRepository<Netflix, String> {

    void deleteAllByExchangeURL(String exchangeUrl);
}
