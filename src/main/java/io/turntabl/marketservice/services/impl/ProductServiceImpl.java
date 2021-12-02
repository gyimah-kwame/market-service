package io.turntabl.marketservice.services.impl;


import io.turntabl.marketservice.dtos.ProductDto;
import io.turntabl.marketservice.models.Product;
import io.turntabl.marketservice.repositories.ProductRepository;
import io.turntabl.marketservice.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductDto::fromModel)
                .collect(Collectors.toList());
    }
}
