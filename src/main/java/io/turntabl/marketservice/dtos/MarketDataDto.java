package io.turntabl.marketservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.turntabl.marketservice.models.MarketData;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MarketDataDto {

    private String ticker;

    private double sellLimit;

    private double lastTradedPrice;

    private double maxPriceShift;

    private double askPrice;

    private double bidPrice;

    private double buyLimit;

    private String exchangeId;

    public static MarketDataDto fromModel(MarketData marketData) {
        MarketDataDto marketDataDto = new MarketDataDto();

        marketDataDto.setAskPrice(marketData.getAskPrice());
        marketDataDto.setBidPrice(marketData.getBidPrice());
        marketDataDto.setBuyLimit(marketDataDto.getBuyLimit());
        marketDataDto.setLastTradedPrice(marketDataDto.getLastTradedPrice());
        marketDataDto.setTicker(marketData.getTicker());
        marketDataDto.setMaxPriceShift(marketDataDto.getMaxPriceShift());
        marketDataDto.setSellLimit(marketDataDto.getSellLimit());

        return marketDataDto;
    }


}
