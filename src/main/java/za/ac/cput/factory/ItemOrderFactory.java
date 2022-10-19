package za.ac.cput.factory;

import za.ac.cput.domain.ItemOrder;

/* ItemOrderFactory.java
 Entity for the ItemOrder
 Author: Blaine Simpson (218020171)
 Date: 01 April 2022

 */
public class ItemOrderFactory {

    public static ItemOrder createItemOrder(String ItemId, String ItemName, String NumberOfPlates){
      return new ItemOrder.Builder().setItemId(ItemId)
              .setItemName(ItemName)
              .setNumberOfPlates(NumberOfPlates)
              .build();



    }
}
