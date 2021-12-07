package io.turntabl.marketservice.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class SubscriptionRequest {

    @NotEmpty
    @JsonProperty("exchange_id")
    private String exchangeId;


}
