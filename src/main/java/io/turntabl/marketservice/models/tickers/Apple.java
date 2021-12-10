package io.turntabl.marketservice.models.tickers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "apple",createIndex = true)
@NoArgsConstructor
public class Apple extends Ticker{

    public Apple(String product, String side, double price, int quantity, String exchangeURL) {
        super(product, side, price, quantity, exchangeURL);
    }

}
