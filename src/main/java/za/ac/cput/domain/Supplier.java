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
public class Supplier implements Serializable {
    @NotNull
    @Id
    private String suppID;
    @NotNull
    private String suppPhysAddress;
    @NotNull
    private String suppEmail;
    @NotNull
    private String suppPhone;
    @NotNull
    private String suppName;



    private Supplier(Builder builder ){
        this.suppName=builder.suppName;
        this.suppPhysAddress=builder.suppPhysAddress;
        this.suppEmail=builder.suppEmail;
        this.suppPhone=builder.suppPhone;
        this.suppID=builder.suppID;

    }

    protected Supplier() {

    }

    public String getSuppName(){
        return suppName;
    }

    public String getSuppPhysAddress(){
        return suppPhysAddress;
    }

    public String getSuppEmail(){
        return suppEmail;
    }

    public String getSuppPhone(){
        return suppPhone;
    }

    public String getSuppID(){
        return suppID;
    }



    @Override
    public String toString(){
        return "Supplier{" +
                "suppName='" + suppID+ '\'' +
                ", supPhysAddress='" + suppPhysAddress + '\'' +
                ", supEmail='" + suppEmail + '\'' +
                ", supPhone='" + suppPhone + '\'' +
                ", supID='" + suppName + '\'' +
                '}';
    }



    @Override
    public int hashCode() {
        return Objects.hash(suppName, suppPhysAddress, suppEmail, suppPhone, suppID);
    }

    public static class Builder{
        private String suppName;

        private String suppPhysAddress;

        private String suppEmail;
        private String suppPhone;
        private String suppID;


        public Builder setSuppName(String suppName){
            this.suppName = suppName;
            return this;
        }

        public Builder setSuppPhysAddress(String suppPhysAddress){
            this.suppPhysAddress = suppPhysAddress;
            return this;
        }

        public Builder setSupEmail(String suppEmail){
            this.suppEmail = suppEmail;
            return this;
        }

        public Builder setSupPhone(String suppPhone){
            this.suppPhone = suppPhone;
            return this;
        }

        public Builder setSuppID(String suppID){
            this.suppID = suppID;
            return this;
        }



        public Builder copy (Supplier supplier){
            this.suppName = supplier.suppName;
            this.suppPhysAddress = supplier.suppPhysAddress;
            this.suppEmail = supplier.suppEmail;
            this.suppPhone = supplier.suppPhone;
            this.suppID = supplier.suppID;
            return this;
        }
        public Supplier build(){
            return new Supplier(this);
        }
    }

}