package io.turntabl.marketservice;

import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.services.ExchangeService;
import io.turntabl.marketservice.services.IRestService;
import io.turntabl.marketservice.services.ServiceContract;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ExchangeTests {

    @Mock
    private ExchangeRepository exchangeRepository;

    @Mock
    private IRestService iRestService;

    @InjectMocks
    private ExchangeService exchangeService = new ExchangeService();

    private final Exchange exchangeOne = new Exchange(1L, "Exchange 1", "https://exchange.com", false);
    private final Exchange exchangeTwo = new Exchange(2L, "Exchange 2", "https://exchange2.com", false);


    @Test
    public void testGetAllExchanges() {
        Mockito.when(exchangeRepository.findAll()).thenReturn(new ArrayList<>(List.of(exchangeOne, exchangeTwo)));
        Assertions.assertEquals(2, exchangeService.getResources().size());
    }


    @Test
    public void testSubscribeToExchange() {

        Mockito.when(exchangeRepository.save(exchangeOne)).thenReturn(exchangeOne);
        Mockito.when(exchangeRepository.save(exchangeTwo)).thenReturn(exchangeTwo);

        Mockito.when(exchangeRepository.findById(1L)).thenReturn(Optional.of(exchangeOne));
        Mockito.when(exchangeRepository.findById(2L)).thenReturn(Optional.of(exchangeTwo));

        exchangeService.subscribeToExchange(exchangeOne.getId());

        System.out.println(exchangeRepository.save(exchangeOne));

        Assertions.assertTrue(exchangeRepository.findById(exchangeOne.getId()).get().isActive());

        exchangeService.subscribeToExchange(exchangeTwo.getId());

        Assertions.assertTrue(exchangeRepository.findById(exchangeTwo.getId()).get().isActive());
    }

    @Test
    public void testUnsubscribeToExchange() {
        Mockito.when(exchangeRepository.findById(1L)).thenReturn(Optional.of(exchangeOne));
        Mockito.when(exchangeRepository.findById(2L)).thenReturn(Optional.of(exchangeTwo));


        Mockito.when(exchangeRepository.save(exchangeOne)).thenReturn(exchangeOne);
        Mockito.when(exchangeRepository.save(exchangeTwo)).thenReturn(exchangeTwo);

        exchangeService.unsubscribeToExchange(exchangeOne.getId());

        Assertions.assertFalse(exchangeRepository.findById(exchangeOne.getId()).get().isActive());

        exchangeService.unsubscribeToExchange(exchangeTwo.getId());

        Assertions.assertFalse(exchangeRepository.findById(exchangeTwo.getId()).get().isActive());
    }
}
