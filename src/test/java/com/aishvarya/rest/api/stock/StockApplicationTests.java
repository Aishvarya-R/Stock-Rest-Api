package com.aishvarya.rest.api.stock;

import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.aishvarya.rest.api.dto.StockVO;
import com.aishvarya.rest.api.entity.StockEntity;
import com.aishvarya.rest.api.exception.StockException;
import com.aishvarya.rest.api.repository.StockInventory;
import com.aishvarya.rest.api.serviceImpl.StockServiceImpl;
import com.aishvarya.rest.api.sort.Sorting;

@SpringBootTest(classes = StockApplication.class)
@WebAppConfiguration
class StockApplicationTests {

    @MockBean
    private StockInventory inventory;
    @MockBean
    private StockServiceImpl service;
    private static List<StockEntity> entityList = new ArrayList<>();

    public void init() {
        entityList.clear();
        StockEntity entity1 = new StockEntity("NITTY", "1002", "1000", "2020-06-10", 7);
        StockEntity entity2 = new StockEntity("MICROSOFT", "1010", "5000", "2020-07-10", 10);
        StockEntity entity3 = new StockEntity("HYUNDAI", "1003", "1500", "2020-05-10", 3);
        entityList.add(entity1);
        entityList.add(entity2);
        entityList.add(entity3);
        inventory = Mockito.mock(StockInventory.class);
        service = Mockito.mock(StockServiceImpl.class);
        ReflectionTestUtils.setField(service, "inventory", inventory);
        Mockito.doReturn(entityList).when(inventory).findAllStock();
        Mockito.when(inventory.findByStockNumber(anyString())).thenReturn(entity3);
        Mockito.doReturn(entity3).when(inventory).save(Mockito.any(entity1.getClass()));
    }

    @Test
    public void createStockTest() throws StockException {
        init();
        StockVO stockVO = new StockVO("HYUNDAI", "1003", "1500", "2020-05-10", 3);
        service.createStock(stockVO);
        Assertions.assertEquals(3, entityList.size());
    }

    @Test
    public void updateStockTest() throws StockException {
        init();
        StockVO stockVO = new StockVO("HYUNDAI", "1003", "1500", "2020-05-10", 3);
        service.updateStock(stockVO, "1003");
        Assertions.assertEquals(3, entityList.size());
    }

    @Test
    public void getStockForIdTest() throws Exception {
        init();
        StockEntity entity3 = new StockEntity("HYUNDAI", "1003", "1500", "2020-05-10", 3);
        ResponseEntity<StockEntity> responseEntity = new ResponseEntity<>(entity3, HttpStatus.OK);
        service.getStockById("1003");
    }

    @Test
    public void getAllStocksDefaultTest() throws StockException {
        init();
        Collections.sort(entityList, Comparator.comparing(StockEntity::getStockNumber));
        service.getAllStocks(Sorting.STOCK_NUMBER);
    }

    @Test
    public void getAllStocksSortingTest() throws StockException {
        init();
        Collections.sort(entityList, Comparator.comparing(StockEntity::getPurchaseDate));
        service.getAllStocks(Sorting.PURCHASE_PRICE);
    }

}
