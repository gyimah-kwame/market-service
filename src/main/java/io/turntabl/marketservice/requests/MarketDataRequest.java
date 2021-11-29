package io.turntabl.marketservice.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MarketDataRequest {

    @JsonProperty("TICKER")
    private String ticker;

    @JsonProperty("SELL_LIMIT")
    private double sellLimit;

    @JsonProperty("LAST_TRADED_PRICE")
    private double lastTradedPrice;

    @JsonProperty("MAX_PRICE_SHIFT")
    private double maxPriceShift;

    @JsonProperty("ASK_PRICE")
    private double askPrice;

    @JsonProperty("BID_PRICE")
    private double bidPrice;

    @JsonProperty("BUY_LIMIT")
    private double buyLimit;

    private String exchangeId;



}
