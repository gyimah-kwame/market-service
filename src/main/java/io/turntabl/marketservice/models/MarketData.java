package io.turntabl.marketservice.models;

import io.turntabl.marketservice.dtos.MarketDataDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

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

    public static MarketData fromDto(MarketDataDto dto) {
        MarketData marketData = new MarketData();

        marketData.setAskPrice(dto.getAskPrice());
        marketData.setBidPrice(dto.getBidPrice());
        marketData.setBuyLimit(dto.getBuyLimit());
        marketData.setLastTradedPrice(dto.getLastTradedPrice());
        marketData.setMaxPriceShift(dto.getMaxPriceShift());
        marketData.setTicker(dto.getTicker());
        marketData.setSellLimit(dto.getSellLimit());
        marketData.setExchangeId("");

        return  marketData;
    }
}
