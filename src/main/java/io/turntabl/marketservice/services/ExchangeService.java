package io.turntabl.marketservice.services;

import io.turntabl.marketservice.dtos.ExchangeDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ExchangeService {

    ExchangeDto subscribeToExchange(String exchangeId);

    ExchangeDto unsubscribeToExchange(String exchangeId);

    List<ExchangeDto> getResources();



}
