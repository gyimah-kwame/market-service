package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.tickers.Amazon;
import io.turntabl.marketservice.models.tickers.Apple;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AmazonRepository extends ElasticsearchRepository<Amazon, String> {

    void deleteAllByExchangeURL(String exchangeUrl);
}
