package za.ac.cput.factory;

/*
IRepository.java
Author: Shuaib Allie (217148867)
Date: 1 April 2022
 */

import za.ac.cput.domain.Inventory;

public class InventoryFactory {

    public static Inventory createInventory(String inv, String itemName, String category, String vendor, int vendorInv, int vendorPrice){
        Inventory inventory = new Inventory.Builder().setInv(inv)
                .setCategory(category)
                .setItemName(itemName)
                .setVendor(vendor)
                .setVendorInv(vendorInv)
                .setVendorPrice(vendorPrice)
                .build();
        return inventory;
    }
}
