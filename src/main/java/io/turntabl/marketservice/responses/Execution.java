package io.turntabl.marketservice.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Execution {

    private LocalDateTime timestamp;
    private double price;
    private int quantity;
}
