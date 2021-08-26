package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.entity.StockTradeEntity;

import java.util.List;

public interface StockTradeService {

    StockTradeEntity createTrade(StockTradeEntity stockTradeEntity);

    List<StockTradeEntity> getTrades(String type, Integer userId);

    StockTradeEntity getTrade(Integer id);
}
