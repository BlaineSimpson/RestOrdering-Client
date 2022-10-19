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
    private String ItemId;
 @NotNull
    private String ItemName;
 @NotNull
    private String NumberOfPlates;


   private ItemOrder(Builder builder){
        this.ItemId = builder.ItemId;
        this.ItemName = builder.ItemName;
        this.NumberOfPlates = builder.NumberOfPlates;

    }

    public ItemOrder(String itemId, String itemName, String NumberOfPlates) {
        ItemId = itemId;
        ItemName = itemName;
        this.NumberOfPlates = NumberOfPlates;
    }

    public ItemOrder() {

    }

    public String getItemId() {
        return ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getPrice() {
        return NumberOfPlates;
    }

    public void setNumberOfPlates(String NumberOfPlates) {
        this.NumberOfPlates = NumberOfPlates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return ItemId.equals(itemOrder.ItemId) && ItemName.equals(itemOrder.ItemName) && NumberOfPlates.equals(itemOrder.NumberOfPlates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ItemId, ItemName, NumberOfPlates);
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemId=" + ItemId +
                ", ItemName='" + ItemName + '\'' +
                ", NumberOfPlates=" + NumberOfPlates +
                '}';
    }

    public static class Builder{

        private String ItemId;
        private String ItemName;
        private String NumberOfPlates;


        public Builder setItemId(String itemId) {
            ItemId = itemId;
            return this;
        }

        public Builder setItemName(String itemName) {
            ItemName = itemName;
            return this;
        }

        public Builder setNumberOfPlates(String NumberOfPlates) {
            this.NumberOfPlates = NumberOfPlates;
            return this;

        }
        public Builder copy(ItemOrder item){
          this.ItemId = item.ItemId;
          this.ItemName =item.ItemName;
          this.NumberOfPlates = item.NumberOfPlates;
          return this;


        }

        public ItemOrder build() {

         return  new ItemOrder(this);
        }


    }
}
