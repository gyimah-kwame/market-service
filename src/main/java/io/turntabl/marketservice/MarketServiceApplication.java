package io.turntabl.marketservice;

import com.google.gson.Gson;
import io.turntabl.marketservice.constants.ExchangeName;
import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.feignclients.ExchangeOneOrderBook;
import io.turntabl.marketservice.feignclients.ExchangeTwoOrderBook;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.repositories.OrderBookRepository;
import io.turntabl.marketservice.responses.OrderBook;
import io.turntabl.marketservice.responses.OrderBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.redis.core.HashOperations;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
@EnableMongoRepositories
@EnableFeignClients
public class MarketServiceApplication  implements CommandLineRunner {

	@Autowired
	private ExchangeRepository exchangeRepository;

	@Autowired
	private HashOperations<String, String, String> hashOperations;

	@Autowired
	private Gson gson;

	@Autowired
	private ExchangeOneOrderBook exchangeOneOrderBook;

	@Autowired
	private ExchangeTwoOrderBook exchangeTwoOrderBook;

	@Autowired
	private OrderBookRepository orderBookRepository;


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

		exchangeRepository.saveAll(exchanges.stream().map(io.turntabl.marketservice.models.Exchange::fromDto).collect(Collectors.toList()));

		hashOperations.put(ExchangeName.EXCHANGE_ONE.toString(), ExchangeName.EXCHANGE_ONE.toString(), gson.toJson(exchangeDto));
		hashOperations.put(ExchangeName.EXCHANGE_TWO.toString(), ExchangeName.EXCHANGE_TWO.toString(), gson.toJson(exchangeDto2));

		orderBookRepository.deleteAll();

		Flux.fromIterable(exchangeOneOrderBook.getData())
				.map(OrderBookResponse::getFullOrderBook)
				.flatMap(Flux::fromIterable)
				.filter(fullOrderBook -> fullOrderBook.getQuantity() - fullOrderBook.getCumulatitiveQuantity() > 0)
				.map(fullOrderBook -> new OrderBook(
								fullOrderBook.getProduct(),
								fullOrderBook.getSide(),
								fullOrderBook.getPrice(),
								fullOrderBook.getQuantity() - fullOrderBook.getCumulatitiveQuantity(),
								"https://exchange.matraining.com"
						)
				).subscribe(orderBook -> orderBookRepository.save(orderBook));

		Flux.fromIterable(exchangeTwoOrderBook.getData())
				.map(OrderBookResponse::getFullOrderBook)
				.flatMap(Flux::fromIterable)
				.filter(fullOrderBook -> fullOrderBook.getQuantity() - fullOrderBook.getCumulatitiveQuantity() > 0)
				.map(fullOrderBook -> new OrderBook(
								fullOrderBook.getProduct(),
								fullOrderBook.getSide(),
								fullOrderBook.getPrice(),
								fullOrderBook.getQuantity() - fullOrderBook.getCumulatitiveQuantity(),
								"https://exchange2.matraining.com"
						)
				).subscribe(orderBook -> orderBookRepository.save(orderBook));


	}
}
