package za.ac.cput.factory;
/* Customer.java
 Author: Sinovuyo Mlanjeni (219220387)
 Date: 04 April 2022
*/

import za.ac.cput.domain.Bill;


public class BillFactory {
    public static Bill createBill(String billId, String billDate,String cusId, String restaurantId,String billDescr, int billAmount){
        Bill bill = new Bill.Builder().setBillId(billId)
                .setBillDate(billDate)
                .setCusId(cusId)
                .setRestaurantId(restaurantId)
                .setBillDescr(billDescr)
                .setBillAmount(billAmount)
                .build();
        return bill;

    }

}


