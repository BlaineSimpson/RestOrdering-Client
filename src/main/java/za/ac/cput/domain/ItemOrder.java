package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/* ItemOrder.java
 Entity for the ItemOrder
 Author: Blaine Simpson (218020171)
 Date: 01 April 2022

 */
@Entity
public class ItemOrder implements Serializable {
 @Id
 @NotNull
    private String itemId;
 @NotNull
    private String itemName;



   private ItemOrder(Builder builder){
        this.itemId = builder.itemId;
        this.itemName = builder.itemName;


    }

    public ItemOrder(String itemId, String itemName, String numberOfPlates) {
        this.itemId = itemId;
        this.itemName = itemName;

    }

    public ItemOrder() {

    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return itemId.equals(itemOrder.itemId) && itemName.equals(itemOrder.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName);
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                '}';
    }

    public static class Builder{

        private String itemId;
        private String itemName;



        public Builder setItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }




        public Builder copy(ItemOrder item){
          this.itemId = item.itemId;
          this.itemName =item.itemName;
          return this;


        }

        public ItemOrder build() {

         return  new ItemOrder(this);
        }


    }
}
