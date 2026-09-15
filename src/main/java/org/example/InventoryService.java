package org.example;

public interface InventoryService {
    int checkStock(String sku);
    int reserveStock(String sku, int qty);
}
