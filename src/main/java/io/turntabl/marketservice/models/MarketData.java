package io.turntabl.marketservice.models;

import io.turntabl.marketservice.dtos.MarketDataDto;
import io.turntabl.marketservice.requests.MarketDataRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document("market_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarketData {

    private String ticker;

    private double sellLimit;

    private double lastTradedPrice;


    private double maxPriceShift;

    private double askPrice;

    private double bidPrice;

    private double buyLimit;

    private String exchangeId;

    private LocalDateTime createdAt = LocalDateTime.now();

    public static MarketData fromDto(MarketDataDto dto) {
        MarketData marketData = new MarketData();

        marketData.setAskPrice(dto.getAskPrice());
        marketData.setBidPrice(dto.getBidPrice());
        marketData.setBuyLimit(dto.getBuyLimit());
        marketData.setLastTradedPrice(dto.getLastTradedPrice());
        marketData.setMaxPriceShift(dto.getMaxPriceShift());
        marketData.setTicker(dto.getTicker());
        marketData.setSellLimit(dto.getSellLimit());
        marketData.setExchangeId(dto.getExchangeId());

        return  marketData;
    }

    public static MarketData fromRequest(MarketDataRequest marketDataRequest, String exchangeId) {
        MarketData marketData = new MarketData();
        marketData.setBuyLimit(marketDataRequest.getBuyLimit());
        marketData.setSellLimit(marketDataRequest.getSellLimit());
        marketData.setTicker(marketDataRequest.getTicker());
        marketData.setExchangeId(exchangeId);
        marketData.setBidPrice(marketDataRequest.getBidPrice());
        marketData.setAskPrice(marketDataRequest.getAskPrice());
        marketData.setLastTradedPrice(marketDataRequest.getLastTradedPrice());

        return marketData;
    }
}
