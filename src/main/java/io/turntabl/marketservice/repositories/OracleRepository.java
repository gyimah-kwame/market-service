package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.tickers.Apple;
import io.turntabl.marketservice.models.tickers.Oracle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OracleRepository extends ElasticsearchRepository<Oracle, String> {
}
