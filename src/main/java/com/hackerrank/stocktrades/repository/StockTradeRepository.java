package com.hackerrank.stocktrades.repository;

import com.hackerrank.stocktrades.entity.StockTradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTradeRepository extends JpaRepository<StockTradeEntity, Integer> {

}
