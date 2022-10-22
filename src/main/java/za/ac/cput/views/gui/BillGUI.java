package za.ac.cput.views.gui;

import za.ac.cput.domain.Bill;
import za.ac.cput.views.requests.BillRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BillGUI {
    private JPanel billPanel;
    private JTextField textFieldBillId;
    private JTextField textFieldBillDate;
    private JTextField textFieldCustomerId;
    private JTextField textFieldRestaurantId;
    private JTextField textFieldBillDescription;
    private JTextField textFieldBillAmount;
    private JButton saveBillButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable billtable;

    public JPanel getPanel(){
        billRequests();
        return billPanel;
    }

    private void billRequests(){
        //save bill
        saveBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillRequest.save(textFieldBillId.getText(), textFieldBillDate.getText(), textFieldCustomerId.getText(), textFieldRestaurantId.getText(), textFieldBillDescription.getText(), Integer.parseInt(textFieldBillAmount.getText()));
                textFieldBillId.setText("");
                textFieldBillDate.setText("");
                textFieldCustomerId.setText("");
                textFieldRestaurantId.setText("");
                textFieldBillDescription.setText("");
                textFieldBillAmount.setText("");
                showTable();
            }
        });
        //View all bills
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTable();
                showTable();

            }
        });
        //Find by Id
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillRequest.viewById(textFieldBillId.getText());
                showTableById();
            }
        });
        //Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillRequest.delete(textFieldBillId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        billtable.setModel(new DefaultTableModel(
                null,
                new String[] {"Bill ID", "Bill Date", "Customer ID", "Restaurant ID", "Description", "Amount"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) billtable.getModel();

        List billCreateList = BillRequest.getAll();
        List<Bill> billList = billCreateList;
        model.setRowCount(0);
        for (int i = 0; i < billList.size(); i++){
            model.addRow(
                    new Object[] {
                            billList.get(i).getBillId(),
                            billList.get(i).getBillDate(),
                            billList.get(i).getCusId(),
                            billList.get(i).getRestaurantId(),
                            billList.get(i).getBillDescr(),
                            billList.get(i).getBillAmount()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) billtable.getModel();
        model.setRowCount(0);

        List billCreateList = BillRequest.getAll();
        List<Bill> billList = billCreateList;

        for (Bill bill : billList) {
            if (bill.getBillId().equals(textFieldBillId.getText())){
                model.addRow(
                        new Object[] {
                                bill.getBillId(),
                                bill.getBillDate(),
                                bill.getCusId(),
                                bill.getRestaurantId(),
                                bill.getBillDescr(),
                                bill.getBillAmount()
                        }
                );
            }
        }
    }
}
