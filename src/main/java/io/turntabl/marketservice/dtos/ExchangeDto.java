package io.turntabl.marketservice.dtos;

import io.turntabl.marketservice.models.Exchange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ExchangeDto {
    private String id;
    private String name;
    private String baseUrl;
    private boolean isActive;

    public static ExchangeDto fromModel(Exchange model) {
        ExchangeDto exchangeDto = new ExchangeDto();

        exchangeDto.setActive(model.isActive());
        exchangeDto.setId(model.getId());
        exchangeDto.setName(model.getName());
        exchangeDto.setBaseUrl(model.getBaseUrl());

        return exchangeDto;
    }
}
