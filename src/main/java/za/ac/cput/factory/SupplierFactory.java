package za.ac.cput.factory;

/*
IRepository.java
Author: Shuaib Allie (217148867)
Date: 1 April 2022
 */

import za.ac.cput.domain.Supplier;
import za.ac.cput.util.LoginHelper;

public class SupplierFactory {
    public static Supplier createSupplier(String suppName, String suppPhysAddress, String suppEmail, String suppPhone, String suppID){
        Supplier supplier = new Supplier.Builder().setSuppName(suppName)
                .setSuppPhysAddress(suppPhysAddress)
                .setSupEmail(suppEmail)
                .setSuppID(suppID)
                .setSupPhone(suppPhone)
                .build();
        LoginHelper.checkStringParam("Supplier Id",suppID);
        LoginHelper.checkStringParam("Supplier Physical  Address",suppPhysAddress);
        LoginHelper.checkStringParam("Supplier Email ",suppEmail );
        LoginHelper.checkStringParam("Supplier Phone",suppPhone);
        LoginHelper.checkIfObjectNull("Supplier Name",suppName );
        return supplier;
    }
}

