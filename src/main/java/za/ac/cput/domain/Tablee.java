/*
Table.java
Table entity
Author Demi Alexis Farquhar (220322104)
Date: 6 April 2022
 */
package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Tablee implements Serializable {

    @Id
    private String tableId;
    @NotNull
    private String tableNo;
    @NotNull
    private String noOfSeats;
    @NotNull
    private boolean isTableAvailable;

    protected Tablee() {
    }

    public String getTableId() {
        return tableId;
    }

    public String getTableNo() {
        return tableNo;
    }

    public String getNoOfSeats() {
        return noOfSeats;
    }

    public boolean isTableAvailable() {
        return isTableAvailable;
    }

    private Tablee(Builder builder) {
        this.tableId = builder.tableId;
        this.tableNo = builder.tableNo;
        this.noOfSeats = builder.noOfSeats;
        this.isTableAvailable = builder.isTableAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tablee table = (Tablee) o;
        return isTableAvailable == table.isTableAvailable && tableId.equals(table.tableId) && tableNo.equals(table.tableNo) && noOfSeats.equals(table.noOfSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableId, tableNo, noOfSeats, isTableAvailable);
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableId='" + tableId + '\'' +
                ", tableNo='" + tableNo + '\'' +
                ", noOfSeats='" + noOfSeats + '\'' +
                ", isTableAvailable=" + isTableAvailable +
                '}';
    }

    public static class Builder {
        private String tableId;
        private String tableNo;
        private String noOfSeats;
        private boolean isTableAvailable;

        public Builder setTableId(String tableId) {
            this.tableId = tableId;
            return this;
        }

        public Builder setTableNo(String tableNo) {
            this.tableNo = tableNo;
            return this;
        }

        public Builder setNoOfSeats(String noOfSeats) {
            this.noOfSeats = noOfSeats;
            return this;
        }

        public Builder setTableAvailable(boolean tableAvailable) {
            isTableAvailable = tableAvailable;
            return this;
        }
        public Builder copy (Tablee table){
            this.tableId=table.tableId;
            this.tableNo=table.tableNo;
            this.noOfSeats=table.noOfSeats;
            this.isTableAvailable=table.isTableAvailable;
            return this;
        }
        public Tablee build(){
            return new Tablee(this);
        }
    }
}