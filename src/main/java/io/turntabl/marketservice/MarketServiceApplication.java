package io.turntabl.marketservice;

import com.google.gson.Gson;
import io.turntabl.marketservice.constants.ExchangeName;
import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.services.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;


@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
@EnableScheduling
public class MarketServiceApplication  implements CommandLineRunner {

	@Autowired
	private ExchangeRepository exchangeRepository;

	@Autowired
	private HashOperations<String, String, String> hashOperations;

	@Autowired
	private Gson gson;

	@Autowired
	private ExchangeService exchangeService;

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
		exchangeDto.setName(ExchangeName.EXCHANGE_ONE.toString());
		exchangeDto.setBaseUrl("https://exchange.matraining.com");


		ExchangeDto exchangeDto2 = new ExchangeDto();
		exchangeDto2.setId("61a7d0ce47593570ea5307b9");
		exchangeDto2.setActive(true);
		exchangeDto2.setName(ExchangeName.EXCHANGE_TWO.toString());
		exchangeDto2.setBaseUrl("https://exchange2.matraining.com");

		List<ExchangeDto> exchanges = List.of(exchangeDto, exchangeDto2);

		exchangeRepository.saveAll(exchanges.stream().map(Exchange::fromDto).collect(Collectors.toList()));

		exchangeService.subscribeToExchange(exchangeDto.getId());
		exchangeService.subscribeToExchange(exchangeDto2.getId());

		hashOperations.put(ExchangeName.EXCHANGE_ONE.toString(), ExchangeName.EXCHANGE_ONE.toString(), gson.toJson(exchangeDto));
		hashOperations.put(ExchangeName.EXCHANGE_TWO.toString(), ExchangeName.EXCHANGE_TWO.toString(), gson.toJson(exchangeDto2));

	}
}
