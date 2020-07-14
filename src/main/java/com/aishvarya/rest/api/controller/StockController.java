package com.aishvarya.rest.api.controller;

import com.aishvarya.rest.api.dto.StockVO;
import com.aishvarya.rest.api.entity.StockEntity;
import com.aishvarya.rest.api.exception.StockException;
import com.aishvarya.rest.api.serviceImpl.StockServiceImpl;
import com.aishvarya.rest.api.sort.Sorting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StockController {
    @Autowired
    private StockServiceImpl stockService;

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void createStock(@RequestBody StockVO stockVO) throws StockException {
        stockService.createStock(stockVO);
    }

    @PutMapping(value = "/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateStock(@PathVariable String id, @RequestBody StockVO stockVO) throws StockException {
        stockService.updateStock(stockVO,id);
    }
    /* http://localhost:8080/stocks/1001 */
    @GetMapping(value = "/stocks/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockEntity> getStockForId(@PathVariable String id) throws StockException {
        return new ResponseEntity<>(stockService.getStockById(id),HttpStatus.OK);
    }
    //http://localhost:8080/stocks
    @GetMapping(value = "/stocks",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<StockEntity> getAllStocksDefault() throws StockException {
        return stockService.getAllStocks(Sorting.STOCK_NUMBER);
    }
    //Allowable values for sortBy are     STOCK_NUMBER / STOCK_NAME / PURCHASE_PRICE / PURCHASE_DATE
    //http://localhost:8080/stocks/sort/STOCK_NAME
    @GetMapping(value = "/stocks/sort/{sortBy}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<StockEntity> getAllStocksSorting(@PathVariable Sorting sortBy) throws StockException {
        return stockService.getAllStocks(sortBy);
    }
}
