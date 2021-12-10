package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.tickers.IBM;
import io.turntabl.marketservice.models.tickers.Microsoft;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBMRepository extends ElasticsearchRepository<IBM, String> {
}
