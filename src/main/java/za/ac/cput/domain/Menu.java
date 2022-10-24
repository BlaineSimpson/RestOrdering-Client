package za.ac.cput.domain;
/* Menu.java
 Entity for the Menu
 Author: Blaine Simpson (218020171)
 Date: 02 April 2022

 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Menu implements Serializable {
    @Id
    private String menuId;
    @NotNull
    private String menuT;

    private Menu(Builder builder){
        this.menuId = builder.menuId;
        this.menuT = builder.menuT;

    }

    public Menu(String menuId, String menuT) {
        this.menuId = menuId;
        this.menuT = menuT;
    }

    public Menu() {

    }

    public String getMenuId() {

        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuT() {
        return menuT;
    }

    public void setMenuT(String menuT) {
        this.menuT = menuT;
    }






    @Override
    public String toString() {
        return "Menu{" +
                "MenuId=" + menuId +
                ", MenuT=" + menuT +
                '}';
    }
    public static class Builder {

        private String menuId;
        private String menuT;

    public Builder setMenuId(String menuId){
        this.menuId = menuId;
        return this;

    }

    public Builder setMenuT(String menut){

        menuT = menut;
        return this;
    }
    public Builder copy(Menu menu){
        this.menuId = menu.menuId;
        this.menuT = menu.menuT;
        return this;

    }

    public Menu build() {

       return new Menu(this);

    }



    }



}
