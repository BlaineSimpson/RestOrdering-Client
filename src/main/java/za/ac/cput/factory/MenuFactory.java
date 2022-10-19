package za.ac.cput.factory;

import za.ac.cput.domain.Menu;


/* MenuFactory.java
 Entity for the Menu
 Author: Blaine Simpson (218020171)
 Date: 02 April 2022

 */
public class MenuFactory {

    public static Menu createMenu(String MenuId, String MenuT){

        return new Menu.Builder().setMenuId(MenuId)
                .setMenuT(MenuT)
                .build();

    }

}