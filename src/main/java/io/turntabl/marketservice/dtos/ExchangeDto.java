package io.turntabl.marketservice.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExchangeDto {
    private Long id;
    private String name;
    private String baseUrl;
    private boolean isActive;
}
