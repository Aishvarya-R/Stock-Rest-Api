package com.aishvarya.rest.api.repository;

import com.aishvarya.rest.api.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockInventory extends JpaRepository<StockEntity,String> {
    @Query(
            value = "SELECT * FROM STOCK_INVENTORY s WHERE s.QUANTITY >= 0",
            nativeQuery = true)
    List<StockEntity> findAllStock();
    StockEntity findByStockNumber(String id);
}
