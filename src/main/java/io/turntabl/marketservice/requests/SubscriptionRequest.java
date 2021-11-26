package io.turntabl.marketservice.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscriptionRequest {

    @NotNull
    @JsonProperty("exchange_id")
    private Long exchangeId;


}
