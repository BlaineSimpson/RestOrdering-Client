/*

Author Shuaib Allie (217148867)

 */

package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Inventory implements Serializable {
    @NotNull
    @Id
    private String inv;
    @NotNull
    private String itemName;;
    @NotNull
    private String category;
    @NotNull
    private String vendor;
    @NotNull
    private String vendorInv;
    @NotNull
    private String vendorPrice;



    private Inventory(Builder builder ){
        this.inv=builder.inv;
        this.itemName=builder.itemName;
        this.category=builder.category;
        this.vendor=builder.vendor;
        this.vendorInv=builder.vendorInv;
        this.vendorPrice=builder.vendorPrice;

    }

    protected Inventory() {

    }

    public String getInv() {
        return inv;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getVendor() {
        return vendor;
    }

    public String getVendorInv(){
        return vendorInv;
    }

    public String getVendorPrice(){
        return vendorPrice;
    }

    @Override
    public String toString(){
        return "Inventory{" +
                "Inventory Type='" + inv + '\'' +
                ", Item Name='" + itemName + '\'' +
                ", Category='" + category + '\'' +
                ", Vendor='" + vendor + '\'' +
                ", Inventory Amount='" + vendorInv + '\'' +
                ", Vendor Price (in Rands)='" + vendorPrice + '\'' +
                '}';
    }



    @Override
    public int hashCode() {
        return Objects.hash(inv, itemName, category, vendor, vendorInv, vendorPrice);
    }

    public static class Builder{
        private String inv;

        private String itemName;

        private String category;
        private String vendor;
        private String vendorInv;
        private String vendorPrice;


        public Builder setInv(String inv){
            this.inv = inv;
            return this;
        }

        public Builder setItemName(String itemName){
            this.itemName = itemName;
            return this;
        }

        public Builder setCategory(String category){
            this.category = category;
            return this;
        }

        public Builder setVendor(String vendor){
            this.vendor = vendor;
            return this;
        }

        public Builder setVendorInv(String vendorInv){
            this.vendorInv = vendorInv;
            return this;
        }

        public Builder setVendorPrice(String vendorPrice){
            this.vendorPrice = vendorPrice;
            return this;
        }



        public Builder copy (Inventory inventory){
            this.inv = inventory.inv;
            this.vendorInv = inventory.vendorInv;
            this.vendorPrice = inventory.vendorPrice;
            this.vendor = inventory.vendor;
            this.itemName = inventory.itemName;
            this.category = inventory.category;
            return this;
        }
        public Inventory build(){
            return new Inventory(this);
        }
    }

}