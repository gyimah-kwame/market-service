package io.turntabl.marketservice.services;

import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExchangeService implements ServiceContract<ExchangeDto, Exchange> {

    @Autowired
    private ExchangeRepository exchangeRepository;


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


}
