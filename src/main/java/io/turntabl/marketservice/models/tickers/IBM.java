package io.turntabl.marketservice.models.tickers;

import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "ibm",createIndex = true)
@NoArgsConstructor
public class IBM extends Ticker{

    public IBM(String product, String side, double price, int quantity, String exchangeURL) {
        super(product, side, price, quantity, exchangeURL);
    }

}
