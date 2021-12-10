package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.tickers.Amazon;
import io.turntabl.marketservice.models.tickers.Apple;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmazonRepository extends ElasticsearchRepository<Amazon, String> {
}
