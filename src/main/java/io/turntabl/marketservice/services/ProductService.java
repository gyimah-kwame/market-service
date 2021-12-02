package io.turntabl.marketservice.services;

import io.turntabl.marketservice.dtos.ProductDto;
import io.turntabl.marketservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    List<ProductDto> getProducts();

}
