package io.turntabl.marketservice.models;


import io.turntabl.marketservice.dtos.ExchangeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document("exchanges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exchange {

    @Id
    private String id;

    private String name;

    @Field(name = "base_url")
    private String baseUrl;

    @Field(name = "is_active")
    private boolean isActive = true;

    public static Exchange fromDto(ExchangeDto dto) {
        Exchange exchange = new Exchange();

        exchange.setActive(dto.isActive());
        exchange.setBaseUrl(dto.getBaseUrl());
        exchange.setName(dto.getName());
        exchange.setId(dto.getId());

        return exchange;
    }
}
