package io.turntabl.marketservice.repositories;

import io.turntabl.marketservice.models.Exchange;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends MongoRepository<Exchange, String> {

    Exchange findByName(String name);


}
