package io.turntabl.marketservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ExchangeDto {
    private Long id;
    private String name;
    private String baseUrl;
    private boolean isActive;
}
