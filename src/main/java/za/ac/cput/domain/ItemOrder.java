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
 @NotNull
    private String numberOfPlates;


   private ItemOrder(Builder builder){
        this.itemId = builder.itemId;
        this.itemName = builder.itemName;
        this.numberOfPlates = builder.numberOfPlates;

    }

    public ItemOrder(String itemId, String itemName, String NumberOfPlates) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.numberOfPlates = NumberOfPlates;
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

    public String getPrice() {
        return numberOfPlates;
    }

    public void setNumberOfPlates(String NumberOfPlates) {
        this.numberOfPlates = NumberOfPlates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrder itemOrder = (ItemOrder) o;
        return itemId.equals(itemOrder.itemId) && itemName.equals(itemOrder.itemName) && numberOfPlates.equals(itemOrder.numberOfPlates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemName, numberOfPlates);
    }

    @Override
    public String toString() {
        return "Item{" +
                "ItemId=" + itemId +
                ", ItemName='" + itemName + '\'' +
                ", NumberOfPlates=" + numberOfPlates +
                '}';
    }

    public static class Builder{

        private String itemId;
        private String itemName;
        private String numberOfPlates;


        public Builder setItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder setNumberOfPlates(String numberOfPlates) {
            this.numberOfPlates = numberOfPlates;
            return this;

        }
        public Builder copy(ItemOrder item){
          this.itemId = item.itemId;
          this.itemName =item.itemName;
          this.numberOfPlates = item.numberOfPlates;
          return this;


        }

        public ItemOrder build() {

         return  new ItemOrder(this);
        }


    }
}
