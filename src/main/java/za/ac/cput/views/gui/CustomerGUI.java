package za.ac.cput.views.gui;

import za.ac.cput.domain.Customer;
import za.ac.cput.views.requests.AdminRequest;
import za.ac.cput.views.requests.CustomerRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerGUI {
    private JPanel customerPanel;
    private JTextField textFieldCustomerId;
    private JTextField textFieldFirstName;
    private JTextField textFieldLastName;
    private JTextField textFieldEmail;
    private JTextField textFieldAddress;
    private JButton saveCustomerButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable customerTable;

    public JPanel getPanel(){
        customerRequests();
        return customerPanel;
    }

    private void customerRequests(){
        //save customer details
        saveCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerRequest.save(textFieldCustomerId.getText(), textFieldFirstName.getText(), textFieldLastName.getText(), textFieldEmail.getText(), textFieldAddress.getText());
                textFieldCustomerId.setText("");
                textFieldFirstName.setText("");
                textFieldLastName.setText("");
                textFieldEmail.setText("");
                textFieldAddress.setText("");
                showTable();
            }
        });
        //View all
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
                CustomerRequest.viewById(textFieldCustomerId.getText());
                showTableById();
            }
        });
        //Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerRequest.delete(textFieldCustomerId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        customerTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "First Name", "Last Name", "Email", "Address"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();

        List customerCreateList = CustomerRequest.getAll();
        List<Customer> customerList = customerCreateList;
        model.setRowCount(0);
        for (int i = 0; i < customerList.size(); i++){
            model.addRow(
                    new Object[] {
                            customerList.get(i).getCusId(),
                            customerList.get(i).getCusFName(),
                            customerList.get(i).getCusLName(),
                            customerList.get(i).getCusEmail(),
                            customerList.get(i).getCusAddress()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) customerTable.getModel();

        List customerCreateList = CustomerRequest.getAll();
        List<Customer> customerList = customerCreateList;
        model.setRowCount(0);

        for (Customer customer: customerList){
            if (customer.getCusId().equals(textFieldCustomerId.getText())){
                model.addRow(
                        new Object[] {
                                customer.getCusId(),
                                customer.getCusFName(),
                                customer.getCusLName(),
                                customer.getCusEmail(),
                                customer.getCusAddress()
                        }
                );
            }
        }
    }
}
