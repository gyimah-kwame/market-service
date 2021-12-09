package io.turntabl.marketservice.models;

import io.turntabl.marketservice.dtos.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String id;

    private String ticker;

    public Product(String ticker) {
        this.ticker = ticker;
    }



    public static Product fromDto(ProductDto productDto){
        Product product = new Product();

        product.id = productDto.getId();
        product.ticker = productDto.getTicker();

        return product;
    }

}