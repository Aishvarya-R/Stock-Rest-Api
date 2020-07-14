package com.aishvarya.rest.api.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="STOCK_INVENTORY")
@Entity
public class StockEntity {
    @Column(name = "STOCK_NAME")
    @NotNull
    private String stockName;
    @Id
    @NotNull
    @Column(name = "STOCK_NUMBER")
    private String stockNumber;
    @Column(name = "PURCHASE_PRICE")
    private String purchasePrice;
    @Column(name = "PURCHASE_DATE")
    private String purchaseDate;
    @Column(name = "QUANTITY")
    private int quantity;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockEntity{" +
                "stockName='" + stockName + '\'' +
                ", stockNumber='" + stockNumber + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public StockEntity(String stockName, String stockNumber,String purchasePrice,String purchaseDate,int quantity){
        this.stockName=stockName;
        this.stockNumber=stockNumber;
        this.purchasePrice=purchasePrice;
        this.purchaseDate=purchaseDate;
        this.quantity=quantity;
    }
    public StockEntity(){

    }
}
