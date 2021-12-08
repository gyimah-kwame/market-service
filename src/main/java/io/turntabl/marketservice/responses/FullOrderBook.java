package io.turntabl.marketservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullOrderBook {

    private String product;
    private int quantity;
    private double price;
    private String side;
    private List<Execution> executions;
    private int cumulatitiveQuantity;

}
