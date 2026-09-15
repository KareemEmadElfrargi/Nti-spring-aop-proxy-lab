package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {
            InventoryService inventoryService = context.getBean("inventoryService", InventoryService.class);
            inventoryService.checkStock("SKU-123");
            inventoryService.reserveStock("SKU-123", 5);
            //inventoryService.reserveStock("SKU-123", 101);
        }
    }
}
