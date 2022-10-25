/*
OrderFactory.java
OrderFactory entity
Author Craig Jarvis (220103216)
Date: 9 April 2022
 */

package za.ac.cput.factory;

import za.ac.cput.domain.Orderr;
import za.ac.cput.util.LoginHelper;

public class OrderFactory {

    public static Orderr createOrder(String orderId, String orderDate, boolean ready){
        Orderr order = new Orderr.Builder().setOrderId(orderId)
                .setOrderDate(orderDate)
                .setReady(ready)
                .build();
        LoginHelper.checkStringParam("Order Id",orderId);
        LoginHelper.checkStringParam("Order Date",orderDate);
        return order;
    }

}
