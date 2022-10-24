/*
RestaurantFactory.java
Author Demi Alexis Farquhar (220322104)
Date: 7 April 2022
 */
package za.ac.cput.factory;

import za.ac.cput.domain.Restaurant;
import za.ac.cput.util.LoginHelper;

public class RestaurantFactory {
    public static Restaurant createRestaurant(String restaurantId, String restaurantName,String restaurantaAdress, boolean isOpen){

        LoginHelper.checkStringParam("Restaurant Id",restaurantId);
        LoginHelper.checkStringParam("Restaurant Name",restaurantName);
        LoginHelper.checkStringParam("Restaurant Address",restaurantaAdress);
        LoginHelper.checkIfObjectNull("is Open", isOpen);

    return new Restaurant.Builder().setRestaurantId(restaurantId).setRestaurantName(restaurantName).setRestaurantAddress(restaurantaAdress).setOpen(isOpen).build();
    }


}
