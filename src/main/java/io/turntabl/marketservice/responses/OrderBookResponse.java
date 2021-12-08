package io.turntabl.marketservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderBookResponse {

    private List<FullOrderBook> fullOrderBook;
}
