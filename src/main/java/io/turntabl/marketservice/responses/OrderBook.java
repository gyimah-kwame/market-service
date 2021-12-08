package io.turntabl.marketservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "order_book",createIndex = true)
public class OrderBook {

    @Id
    private String id;

    @Field(type = FieldType.Text, name = "product")
    private String product;

    @Field(type = FieldType.Text, name = "side")
    private String side;

    @Field(type = FieldType.Double, name = "price")
    private double price;

    @Field(type = FieldType.Integer, name = "quantity")
    private int quantity;

    @Field(type = FieldType.Text, name = "exchangeURL")
    private String exchangeURL;

    @Field(type = FieldType.Text, name = "timestamp")
    private String localDateTime = LocalDateTime.now().toString();

    public OrderBook(String product, String side, double price, int quantity, String exchangeURL) {
        this.product = product;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.exchangeURL = exchangeURL;
    }
}
