package com.hackerrank.stocktrades.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Timestamp;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTrade {

    private Integer id;

    @NotBlank
    @Pattern(regexp = "^(buy|sell)$")
    private String type;

    @NotNull
    private Integer userId;

    @NotNull
    private String symbol;

    @NotNull
    @Min(1)
    @Max(100)
    private Integer shares;

    @NotNull
    private Integer price;

    @NotNull
    private Long timestamp;

    public StockTrade(String type, Integer userId, String symbol, Integer shares, Integer price, Long timestamp) {
        this.type = type;
        this.userId = userId;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.timestamp = timestamp;
    }

    public Timestamp getTimestamp() {
        return new Timestamp(this.timestamp);
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp.getTime();
    }

}
