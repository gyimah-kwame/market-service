package io.turntabl.marketservice.dtos;

import io.turntabl.marketservice.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;

    private String ticker;


    public static ProductDto fromModel(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setTicker(product.getTicker());

        return productDto;
    }
}