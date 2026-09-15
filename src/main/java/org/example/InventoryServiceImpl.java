package org.example;

public class InventoryServiceImpl implements InventoryService {
    @Override
    public int checkStock(String sku) {
        System.out.println("Check stock in a real method " + sku);
        return 0;
    }

    @Override
    public int reserveStock(String sku, int qty){
        System.out.println("Reserve stock in a real method " + sku);
        if (qty >100) {
            throw new IllegalArgumentException("Quantity must be less than 100: " + qty);
        }
        return 0;
    }
}
