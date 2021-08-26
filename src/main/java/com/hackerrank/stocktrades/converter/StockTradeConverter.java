package com.hackerrank.stocktrades.converter;

import com.hackerrank.stocktrades.entity.StockTradeEntity;
import com.hackerrank.stocktrades.model.StockTrade;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockTradeConverter {

    private ModelMapper modelMapper = new ModelMapper();

    public StockTradeEntity convertToEntity(StockTrade stockTrade) {
        StockTradeEntity stockTradeEntity = modelMapper.map(stockTrade, StockTradeEntity.class);
        stockTradeEntity.setTimestamp(stockTrade.getTimestamp());
        return stockTradeEntity;
    }

    public StockTrade convertToDto(StockTradeEntity stockTradeEntity) {
        StockTrade stockTrade = modelMapper.map(stockTradeEntity, StockTrade.class);
        stockTrade.setTimestamp(stockTradeEntity.getTimestamp());
        return stockTrade;
    }

    public List<StockTrade> convertToDtoList(List<StockTradeEntity> stockTradeEntities) {
        return stockTradeEntities
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


}
