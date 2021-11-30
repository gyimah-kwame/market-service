package io.turntabl.marketservice;

import io.turntabl.marketservice.dtos.ExchangeDto;
import io.turntabl.marketservice.models.Exchange;
import io.turntabl.marketservice.repositories.ExchangeRepository;
import io.turntabl.marketservice.services.ExchangeService;
import io.turntabl.marketservice.services.impl.ExchangeServiceImpl;
import io.turntabl.marketservice.rest.IRestService;
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

    @Mock
    private ExchangeService exchangeService;


    private final Exchange exchangeOne = new Exchange("1", "Exchange 1", "https://exchange.com", false);
    private final Exchange exchangeTwo = new Exchange("2", "Exchange 2", "https://exchange2.com", false);


    private final ExchangeDto exchangeDtoOne = new ExchangeDto("1", "Exchange 1", "https://exchange.com", false);
    private final ExchangeDto exchangeDtoTwo = new ExchangeDto("2", "Exchange 2", "https://exchange2.com", false);


    @Test
    public void testGetAllExchanges() {
        Mockito.when(exchangeService.getResources()).thenReturn(new ArrayList<>(List.of(exchangeDtoOne, exchangeDtoTwo)));
        Assertions.assertEquals(2, exchangeService.getResources().size());
    }


    @Test
    public void testSubscribeToExchange() {

        Mockito.when(exchangeRepository.findById("1")).thenReturn(Optional.of(exchangeOne));
        Mockito.when(exchangeRepository.findById("2")).thenReturn(Optional.of(exchangeTwo));

        Mockito.when(exchangeService.subscribeToExchange(exchangeOne.getId())).thenReturn(exchangeDtoOne);
        Mockito.when(exchangeService.subscribeToExchange(exchangeTwo.getId())).thenReturn(exchangeDtoTwo);

        exchangeService.subscribeToExchange(exchangeOne.getId());

        exchangeOne.setActive(true);

        Assertions.assertTrue(exchangeRepository.findById(exchangeOne.getId()).get().isActive());

        exchangeService.subscribeToExchange(exchangeTwo.getId());

        exchangeTwo.setActive(true);

        Assertions.assertTrue(exchangeRepository.findById(exchangeTwo.getId()).get().isActive());
    }

    @Test
    public void testUnsubscribeToExchange() {
        Mockito.when(exchangeRepository.findById("1")).thenReturn(Optional.of(exchangeOne));
        Mockito.when(exchangeRepository.findById("2")).thenReturn(Optional.of(exchangeTwo));

        Mockito.when(exchangeService.unsubscribeToExchange(exchangeOne.getId())).thenReturn(exchangeDtoOne);
        Mockito.when(exchangeService.unsubscribeToExchange(exchangeTwo.getId())).thenReturn(exchangeDtoTwo);

        exchangeService.unsubscribeToExchange(exchangeOne.getId());

        exchangeOne.setActive(false);

        Assertions.assertFalse(exchangeRepository.findById(exchangeOne.getId()).get().isActive());

        exchangeService.unsubscribeToExchange(exchangeTwo.getId());

        exchangeTwo.setActive(false);

        Assertions.assertFalse(exchangeRepository.findById(exchangeTwo.getId()).get().isActive());
    }
}
