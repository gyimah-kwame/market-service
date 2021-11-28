package io.turntabl.marketservice.services;

import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.exceptions.InvalidExchangeException;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.rest.IRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExchangeService implements ServiceContract<ExchangeDto, Exchange> {

    @Value("${environments.test.url}")
    private String serverUrl;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private IRestService iRestService;


    @Override
    public List<ExchangeDto> getResources() {
       return exchangeRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ExchangeDto storeResource(ExchangeDto model) {
        return null;
    }

    @Override
    public Optional<ExchangeDto> findResource(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteResource(Long id) {
        return false;
    }

    @Override
    public ExchangeDto updateResource(Long id) {
        Optional<Exchange> exchange = exchangeRepository.findById(id);

        if (exchange.isEmpty()) {
            return  null;
        }

        return toDto(exchange.get());
    }

    @Override
    public Exchange toModel(ExchangeDto dto) {
        Exchange exchange = new Exchange();

        exchange.setActive(dto.isActive());
        exchange.setBaseUrl(dto.getBaseUrl());
        exchange.setName(dto.getName());
        exchange.setId(dto.getId());

        return exchange;
    }

    @Override
    public ExchangeDto toDto(Exchange model) {
        ExchangeDto exchangeDto = new ExchangeDto();

        exchangeDto.setActive(model.isActive());
        exchangeDto.setId(model.getId());
        exchangeDto.setName(model.getName());
        exchangeDto.setBaseUrl(model.getBaseUrl());

        return exchangeDto;
    }

    public ExchangeDto subscribeToExchange(long exchangeId) {
        return toggleSubscription(exchangeId, true);
    }

    public ExchangeDto unsubscribeToExchange(long exchangeId) {
        return toggleSubscription(exchangeId, false);
    }

    public ExchangeDto toggleSubscription(Long exchangeId, boolean status) {
        Optional<Exchange> data = exchangeRepository.findById(exchangeId);

        if (data.isEmpty()) {

            throw new InvalidExchangeException(exchangeId);
        }

        Exchange exchange = data.get();

        String callback = exchange.getName().equalsIgnoreCase("exchange 1") ? serverUrl+"/api/exchanges/callback_one": serverUrl+"/api/exchanges/callback_two";

        iRestService.toggleSubscription(exchange.getBaseUrl(), callback, status ? "POST":"DELETE");

        exchange.setActive(status);

        return toDto(exchangeRepository.save(exchange));
    }


}
