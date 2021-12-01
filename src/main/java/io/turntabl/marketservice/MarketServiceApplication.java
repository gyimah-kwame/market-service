package io.turntabl.marketservice;

import io.turntabl.marketservice.constants.AppConstants;
import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.services.ExchangeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;
import java.util.stream.Collectors;


@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableMongoRepositories

public class MarketServiceApplication  implements CommandLineRunner {

	@Autowired
	private ExchangeRepository exchangeRepository;


	public static void main(String[] args){
		SpringApplication.run(MarketServiceApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		//seed exchanges to database

		exchangeRepository.deleteAll();

		ExchangeDto exchangeDto = new ExchangeDto();
		exchangeDto.setActive(true);
		exchangeDto.setId("61a7d0ce47593570ea5307b8");
		exchangeDto.setName(AppConstants.EXCHANGE_ONE);
		exchangeDto.setBaseUrl("https://exchange.matraining.com");

		exchangeDto.setId("61a7d0ce47593570ea5307b9");
		ExchangeDto exchangeDto2 = new ExchangeDto();
		exchangeDto2.setActive(true);
		exchangeDto2.setName(AppConstants.EXCHANGE_TWO);
		exchangeDto2.setBaseUrl("https://exchange2.matraining.com");

		List<ExchangeDto> exchanges = List.of(exchangeDto, exchangeDto2);

		exchangeRepository.saveAll(exchanges.stream().map(Exchange::fromDto).collect(Collectors.toList()));
	}
}
