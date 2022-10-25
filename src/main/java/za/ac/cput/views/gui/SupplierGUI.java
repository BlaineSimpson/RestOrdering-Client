package za.ac.cput.views.gui;

import za.ac.cput.domain.Supplier;
import za.ac.cput.views.requests.AdminRequest;
import za.ac.cput.views.requests.SupplierRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SupplierGUI {
    private JPanel supplierPanel;
    private JTextField textFieldSupplierId;
    private JTextField textFieldName;
    private JTextField textFieldAddress;
    private JTextField textFieldEmail;
    private JTextField textFieldPhone;
    private JButton saveButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable supplierTable;
    private JLabel id;
    private JLabel name;
    private JLabel address;
    private JLabel email;
    private JLabel phione;
    private Font f1,f2;

    public JPanel getPanel(){
        supplierRequests();
        createTable();
        showTable();
        f1=new Font("Arial",Font.PLAIN,14);
        f2=new Font("Arial",Font.BOLD,14);
        saveButton.setFont(f2);
        viewAllButton.setFont(f2);
        findButton.setFont(f2);
        deleteButton.setFont(f2);
        name.setFont(f1);
        address.setFont(f1);
        email.setFont(f1);
        id.setFont(f1);
        phione.setFont(f1);
        return supplierPanel;
    }

    private void supplierRequests(){
        //save supplier details
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierRequest.save(textFieldName.getText(), textFieldAddress.getText(), textFieldEmail.getText(), textFieldPhone.getText(),  textFieldSupplierId.getText());
                textFieldSupplierId.setText("");
                textFieldName.setText("");
                textFieldAddress.setText("");
                textFieldEmail.setText("");
                textFieldPhone.setText("");
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
                SupplierRequest.viewById(textFieldSupplierId.getText());
                showTableById();
            }
        });
        //Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierRequest.delete(textFieldSupplierId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        supplierTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "Supplier Name", "Address", "Email", "Phone"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();

        List supplierCreateList = SupplierRequest.getAll();
        List<Supplier> supplierList = supplierCreateList;
        model.setRowCount(0);
        for (int i = 0; i < supplierList.size(); i++){
            model.addRow(
                    new Object[] {
                            supplierList.get(i).getSuppID(),
                            supplierList.get(i).getSuppName(),
                            supplierList.get(i).getSuppPhysAddress(),
                            supplierList.get(i).getSuppEmail(),
                            supplierList.get(i).getSuppPhone()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();

        List supplierCreateList = SupplierRequest.getAll();
        List<Supplier> supplierList = supplierCreateList;
        model.setRowCount(0);
        for (Supplier supplier : supplierList){
            if (supplier.getSuppID().equals(textFieldSupplierId.getText())){
                model.addRow(
                        new Object[] {
                                supplier.getSuppID(),
                                supplier.getSuppName(),
                                supplier.getSuppPhysAddress(),
                                supplier.getSuppEmail(),
                                supplier.getSuppPhone()
                        }
                );
            }
        }
    }
}
