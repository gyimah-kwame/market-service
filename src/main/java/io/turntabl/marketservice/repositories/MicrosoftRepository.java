package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.tickers.Google;
import io.turntabl.marketservice.models.tickers.Microsoft;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MicrosoftRepository extends ElasticsearchRepository<Microsoft, String> {

    void deleteAllByExchangeURL(String exchangeUrl);
}
