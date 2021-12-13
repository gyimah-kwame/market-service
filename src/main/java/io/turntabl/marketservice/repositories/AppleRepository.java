package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.tickers.Apple;
import io.turntabl.marketservice.models.tickers.IBM;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppleRepository extends ElasticsearchRepository<Apple, String> {

    void deleteAllByExchangeURL(String exchangeUrl);
}
