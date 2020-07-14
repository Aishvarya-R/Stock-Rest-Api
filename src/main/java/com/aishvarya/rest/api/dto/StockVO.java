package com.aishvarya.rest.api.dto;

import java.util.Objects;

public class StockVO {
    private String stockName;
    private String stockNumber;
    private String purchasePrice;
    private String purchaseDate;
    private int quantity;

    public StockVO(String stockName, String stockNumber, String purchasePrice, String purchaseDate, int quantity) {
        this.stockName = stockName;
        this.stockNumber = stockNumber;
        this.purchasePrice = purchasePrice;
        this.purchaseDate = purchaseDate;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockVO{" +
                "stockName='" + stockName + '\'' +
                ", stockNumber='" + stockNumber + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockVO)) return false;
        StockVO stockVO = (StockVO) o;
        return Objects.equals(getStockNumber(), stockVO.getStockNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStockNumber());
    }

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
}
