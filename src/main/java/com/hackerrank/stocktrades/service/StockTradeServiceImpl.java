package com.hackerrank.stocktrades.service;

import com.hackerrank.stocktrades.entity.StockTradeEntity;
import com.hackerrank.stocktrades.exception.StockTradeDBException;
import com.hackerrank.stocktrades.exception.StockTradeDataNotFoundException;
import com.hackerrank.stocktrades.repository.StockTradeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StockTradeServiceImpl implements StockTradeService {

    private final StockTradeRepository stockTradeRepository;

    private final EntityManager entityManager;

    @Autowired
    public StockTradeServiceImpl(StockTradeRepository stockTradeRepository, EntityManager entityManager) {
        this.stockTradeRepository = stockTradeRepository;
        this.entityManager = entityManager;
    }

    public StockTradeEntity createTrade(StockTradeEntity stockTradeEntity) {
        try {
            return stockTradeRepository.save(stockTradeEntity);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new StockTradeDBException(ex.getMessage());
        }
    }

    public List<StockTradeEntity> getTrades(String type, Integer userId) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery cq = criteriaBuilder.createQuery();
            Root<StockTradeEntity> stockTradeRoot = cq.from(StockTradeEntity.class);
            List<Predicate> predicates = new ArrayList<>();
            if (type != null) {
                predicates.add(
                        criteriaBuilder.equal(stockTradeRoot.get("type"), type));
            }
            if (userId != null) {
                predicates.add(
                        criteriaBuilder.equal(stockTradeRoot.get("userId"), userId));
            }
            cq.select(stockTradeRoot)
                    .where(predicates.toArray(new Predicate[]{}));
            return entityManager.createQuery(cq).getResultList();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new StockTradeDBException(ex.getMessage());
        }
    }

    public StockTradeEntity getTrade(Integer id) {
        try {
            return stockTradeRepository.findById(id).orElseThrow(() -> new StockTradeDataNotFoundException("No Data found"));
        } catch (StockTradeDataNotFoundException ex) {
            log.error(ex.getMessage());
            throw new StockTradeDataNotFoundException(ex.getMessage());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new StockTradeDBException(ex.getMessage());
        }

    }


}
