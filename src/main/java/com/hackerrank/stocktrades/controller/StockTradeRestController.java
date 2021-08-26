package com.hackerrank.stocktrades.controller;

import com.hackerrank.stocktrades.converter.StockTradeConverter;
import com.hackerrank.stocktrades.entity.StockTradeEntity;
import com.hackerrank.stocktrades.model.StockTrade;
import com.hackerrank.stocktrades.service.StockTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/trades")
public class StockTradeRestController {

    private final StockTradeService stockTradeService;

    private final StockTradeConverter stockTradeConverter;

    @Autowired
    public StockTradeRestController(StockTradeService stockTradeService, StockTradeConverter stockTradeConverter) {
        this.stockTradeService = stockTradeService;
        this.stockTradeConverter = stockTradeConverter;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public StockTrade createTrade(@Valid @RequestBody StockTrade stockTrade) {
        StockTradeEntity stockTradeEntity = stockTradeConverter.convertToEntity(stockTrade);
        StockTradeEntity stockTradeEntityCreated = stockTradeService.createTrade(stockTradeEntity);
        return stockTradeConverter.convertToDto(stockTradeEntityCreated);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<StockTrade> getTrades(@RequestParam(required = false) String type, @RequestParam(required = false) Integer userId) {
        List<StockTradeEntity> trades = stockTradeService.getTrades(type, userId);
        return stockTradeConverter.convertToDtoList(trades);
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public StockTrade getTrade(@PathVariable Integer id) {
        StockTradeEntity tradeEntity = stockTradeService.getTrade(id);
        return stockTradeConverter.convertToDto(tradeEntity);
    }

}