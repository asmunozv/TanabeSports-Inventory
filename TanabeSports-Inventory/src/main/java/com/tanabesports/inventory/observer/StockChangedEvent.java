package com.tanabesports.inventory.observer;
public record StockChangedEvent(Long productId,String productName,int oldStock,int newStock,int minimumStock) {}
