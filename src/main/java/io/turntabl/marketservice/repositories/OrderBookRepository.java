package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.responses.OrderBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBookRepository extends ElasticsearchRepository<OrderBook, String> {
}
