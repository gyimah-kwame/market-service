package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.OrderBook;
import io.turntabl.marketservice.models.tickers.Google;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleRepository extends ElasticsearchRepository<Google, String> {

    void deleteAllByExchangeURL(String exchangeUrl);
}
