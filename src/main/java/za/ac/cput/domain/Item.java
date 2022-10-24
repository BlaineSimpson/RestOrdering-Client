/*
Item.java
Item entity
Author Craig Jarvis (220103216)
Date: 9 April 2022
 */

package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Item implements Serializable {
    @Id
    @NotNull
    private String id;
    @NotNull
    private String orderID;
    @NotNull
    private String quantity;
    @NotNull
    private String price;




    private Item(Builder builder){
        this.id =builder.id;
        this.orderID=builder.orderID;
        this.quantity=builder.quantity;
        this.price=builder.price;
    }

    protected Item() {

    }


    //getters and setters
    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID( String orderID) {
        this.orderID = orderID;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    //end of getters and setters


    @Override
    public String toString() {
        return "Item{" +
                "ID=" + id +
                ", orderID=" + orderID +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
    //end of toString


    public static class Builder{
        private String id;
        private  String orderID;
        private  String quantity;
        private  String price;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setOrderID( String orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice( String price) {
            this.price = price;
            return this;
        }

        public Builder copy(Item item){
            this.id =item.id;
            this.orderID=item.orderID;
            this.quantity=item.quantity;
            this.price=item.price;
            return this;
        }
        public Item build(){
            return new Item(this);
        }
    }
}