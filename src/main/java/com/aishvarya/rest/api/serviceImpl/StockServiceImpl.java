package com.aishvarya.rest.api.serviceImpl;

import com.aishvarya.rest.api.dto.StockVO;
import com.aishvarya.rest.api.entity.StockEntity;
import com.aishvarya.rest.api.exception.StockException;
import com.aishvarya.rest.api.repository.StockInventory;
import com.aishvarya.rest.api.sort.Sorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class StockServiceImpl {
    @Autowired
    private StockInventory inventory;
    public void createStock(StockVO stockVO) throws StockException {
        StockEntity stockEntity = toStockEntity(stockVO);
        try {
            inventory.save(stockEntity);
        } catch (Exception ex){
            throw new StockException("Error while creating a stock: "+stockVO.toString());
        }
    }

    public StockEntity toStockEntity(StockVO stockVO){
        return new StockEntity(stockVO.getStockName(), stockVO.getStockNumber(),
                stockVO.getPurchasePrice(),stockVO.getPurchaseDate(),stockVO.getQuantity());
    }

    public void updateStock(StockVO stockVO, String id) throws StockException {
        StockEntity entity = getStockById(id);
        updateStockEntity(entity,stockVO);
    }

    public StockEntity getStockById(String id) throws StockException {
        StockEntity entity = null;
        try{
            entity= inventory.findByStockNumber(id);
        } catch (Exception ex){
            throw new StockException("Error while retriving a stock: "+id);
        }
        if(entity==null)
            throw new StockException("There is no entry for the given stock number: "+id);
        return entity;
    }

    public void updateStockEntity(StockEntity entity, StockVO stockVO) throws StockException {
        entity.setStockName(stockVO.getStockName());
        entity.setStockNumber(stockVO.getStockNumber());
        entity.setPurchaseDate(stockVO.getPurchaseDate());
        entity.setPurchasePrice(stockVO.getPurchasePrice());
        entity.setQuantity(stockVO.getQuantity());
        try {
            inventory.save(entity);
            if(entity.getQuantity()==0){
                inventory.delete(entity);
            }
        } catch (Exception ex){
            throw new StockException("Error while updating a stock: "+stockVO.getStockNumber());
        }
    }

    public List<StockEntity> getAllStocks(Sorting sortBy) throws StockException {
        List<StockEntity> entities;
        try{
            entities=inventory.findAllStock();
        } catch (Exception ex){
            throw new StockException("Error while retriving all stocks");
        }
        return sortAllStocks(entities,sortBy);
    }

    public List<StockEntity> sortAllStocks(List<StockEntity> entities, Sorting sortBy){
        switch(sortBy) {
            case STOCK_NUMBER:
                Collections.sort(entities, Comparator.comparing(StockEntity::getStockNumber));
                break;
            case STOCK_NAME:
                Collections.sort(entities, Comparator.comparing(StockEntity::getStockName));
                break;
            case PURCHASE_PRICE:
                Collections.sort(entities, Comparator.comparing(StockEntity::getPurchasePrice));
                break;
            case PURCHASE_DATE:
                Collections.sort(entities, Comparator.comparing(StockEntity::getPurchaseDate));
                break;
        }
        return entities;
    }
}
