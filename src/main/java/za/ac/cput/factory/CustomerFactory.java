package za.ac.cput.factory;
/* Customer .java
 Entity for the Customer
 Author: Sinovuyo Mlanjeni (219220387)
 Date: 04 April 2022
*/
import za.ac.cput.domain.Customer;
import za.ac.cput.util.LoginHelper;


public class CustomerFactory {
    public static Customer createCustomer(String cusId, String cusFName, String cusLName, String cusEmail, String cusAddress){
        Customer customer = new Customer.Builder().setCusId(cusId)
                .setCusFName(cusFName)
                .setCusLName(cusLName)
                .setCusEmail(cusEmail)
                .setCusAddress(cusAddress)
                .build();
        LoginHelper.checkStringParam("Customer Id",cusId);
        LoginHelper.checkStringParam("Customer First Name",cusFName);
        LoginHelper.checkStringParam("Customer Last NAme",cusLName);
        LoginHelper.checkStringParam("Customer Email",cusEmail );
        LoginHelper.checkStringParam("Customer Address",cusAddress);

        return customer;
    }

}
