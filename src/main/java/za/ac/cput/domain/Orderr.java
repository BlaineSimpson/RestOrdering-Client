/*
Order.java
Order entity
Author Craig Jarvis (220103216)
Date: 9 April 2022
 */

package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Orderr implements Serializable {
    @Id
    @NotNull
    private String orderID;
    @NotNull
    private String orderDate;
    @NotNull
    private boolean isReady;

   protected Orderr(){
    }


    private Orderr(Builder builder){
        this.orderID=builder.orderID;
        this.orderDate=builder.orderDate;
        this.isReady=builder.isReady;
    }


    //getters and setters
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
    //end of getters and setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderr order = (Orderr) o;
        return isReady == order.isReady && orderID.equals(order.orderID) && orderDate.equals(order.orderDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, orderDate, isReady);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", isReady=" + isReady +
                '}';
    }
//end of toString



    public static class Builder{
        private String orderID;
        private String orderDate;
        private boolean isReady;

        public Builder setOrderId(String orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setOrderDate(String orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setReady(boolean ready) {
            isReady = ready;
            return this;
        }

        public Builder copy (Orderr order){
            this.orderID=order.orderID;
            this.orderDate=order.orderDate;
            this.isReady=order.isReady;
            return this;
        }
        public Orderr build(){
            return new Orderr(this);
        }
    }
}
