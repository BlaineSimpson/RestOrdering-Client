/*
ItemFactory.java
ItemFactory entity
Author Craig Jarvis (220103216)
Date: 9 April 2022
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Item;

public class ItemFactory {

    public static Item createItem(String orderId, String orderID, String numStock, String price){
        Item item = new Item.Builder().setId(orderID)
                .setOrderID(orderID)
                .setQuantity(numStock)
                .setPrice(price)
                .build();
        return item;
    }

}
