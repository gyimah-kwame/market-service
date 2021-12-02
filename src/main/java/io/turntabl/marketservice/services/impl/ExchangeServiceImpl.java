package io.turntabl.marketservice.services.impl;

import io.turntabl.marketservice.constants.ExchangeName;
import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.exceptions.InvalidExchangeException;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.rest.IRestService;
import io.turntabl.marketservice.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {

    @Value("${environments.test.url}")
    private String serverUrl;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private IRestService iRestService;



    @Override
    public List<ExchangeDto> getResources() {
       return exchangeRepository.findAll().stream().map(ExchangeDto::fromModel).collect(Collectors.toList());
    }

    public ExchangeDto storeResource(ExchangeDto dto) {
       Exchange exchange = new Exchange();
       exchange.setActive(dto.isActive());
       exchange.setName(dto.getName());
       exchange.setBaseUrl(dto.getBaseUrl());

       return ExchangeDto.fromModel(exchangeRepository.insert(exchange));
    }



    @Override
    public ExchangeDto subscribeToExchange(String exchangeId) {
        return toggleSubscription(exchangeId, true);
    }

    @Override
    public ExchangeDto unsubscribeToExchange(String exchangeId) {
        return toggleSubscription(exchangeId, false);
    }

    public ExchangeDto toggleSubscription(String exchangeId, boolean status) {
        Optional<Exchange> data = exchangeRepository.findById(exchangeId);

        Exchange exchange = data.orElseThrow(() -> new InvalidExchangeException(exchangeId));

        String callback = exchange.getName().equalsIgnoreCase(ExchangeName.EXCHANGE_ONE.toString()) ? serverUrl+"/api/v1/exchanges/callback_one": serverUrl+"/api/v1/exchanges/callback_two";

        iRestService.toggleSubscription(exchange.getBaseUrl(), callback, status ? "POST":"DELETE");

        exchange.setActive(status);

        return ExchangeDto.fromModel(exchangeRepository.save(exchange));
    }


}
