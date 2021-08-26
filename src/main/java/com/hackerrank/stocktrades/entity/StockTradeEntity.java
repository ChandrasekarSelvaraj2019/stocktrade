package com.hackerrank.stocktrades.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "stock_trade")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTradeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "shares")
    private Integer shares;

    @Column(name = "price")
    private Integer price;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    public StockTradeEntity(String type, Integer userId, String symbol, Integer shares, Integer price, Long timestamp) {
        this.type = type;
        this.userId = userId;
        this.symbol = symbol;
        this.shares = shares;
        this.price = price;
        this.timestamp = new Timestamp(timestamp);
    }


}
