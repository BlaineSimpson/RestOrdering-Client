package za.ac.cput.views.gui;

import za.ac.cput.domain.Inventory;
import za.ac.cput.views.requests.InventoryRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InventoryGUI {
    private JPanel inventoryPanel;
    private JTextField textFieldInventoryId;
    private JTextField textFieldItemName;
    private JTextField textFieldCategory;
    private JTextField textFieldVendor;
    private JTextField textFieldVendorInv;
    private JTextField textFieldPrice;
    private JButton saveInventoryButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable inventoryTable;
    private JLabel id;
    private JLabel name;
    private JLabel category;
    private JLabel vendor;
    private JLabel inv;
    private JLabel price;
    private JComboBox comboBox1;
    private Font f1,f2;

    public JPanel getPanel(){
        inventoryRequests();
        createTable();
        showTable();
        f1=new Font("Arial",Font.PLAIN,14);
        f2=new Font("Arial",Font.BOLD,14);
        id.setFont(f1);
        category.setFont(f1);
        inv.setFont(f1);
        price.setFont(f1);
        comboBox1.setFont(f1);
        name.setFont(f1);
        vendor.setFont(f1);
        viewAllButton.setFont(f2);
        deleteButton.setFont(f2);
        findButton.setFont(f2);
        saveInventoryButton.setFont(f2);
        return inventoryPanel;
    }

    private void inventoryRequests(){
        //save inventory details
        saveInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryRequest.save(textFieldInventoryId.getText(), textFieldItemName.getText(),
                        (String) comboBox1.getSelectedItem(), textFieldVendor.getText(),
                        textFieldVendorInv.getText(), textFieldPrice.getText());
                textFieldInventoryId.setText("");
                textFieldItemName.setText("");
                comboBox1.setSelectedIndex(0);
                textFieldVendor.setText("");
                textFieldVendorInv.setText("");
                textFieldPrice.setText("");
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
                InventoryRequest.viewById(textFieldInventoryId.getText());
                showTableById();
            }
        });
        //Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InventoryRequest.delete(textFieldInventoryId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        inventoryTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "Item Name", "Category", "Vendor", "Quantity", "Vendor Price"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();

        List inventoryCreateList = InventoryRequest.getAll();
        List<Inventory> inventoryList = inventoryCreateList;
        model.setRowCount(0);
        for (int i = 0; i < inventoryList.size(); i++){
            model.addRow(
                    new Object[] {
                            inventoryList.get(i).getInv(),
                            inventoryList.get(i).getItemName(),
                            inventoryList.get(i).getCategory(),
                            inventoryList.get(i).getVendor(),
                            inventoryList.get(i).getVendorInv(),
                            inventoryList.get(i).getVendorPrice()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();

        List inventoryCreateList = InventoryRequest.getAll();
        List<Inventory> inventoryList = inventoryCreateList;
        model.setRowCount(0);
        for (Inventory inventory : inventoryList) {
            if (inventory.getInv().equals(textFieldInventoryId.getText())){
                model.addRow(
                        new Object[] {
                                inventory.getInv(),
                                inventory.getItemName(),
                                inventory.getCategory(),
                                inventory.getVendor(),
                                inventory.getVendorInv(),
                                inventory.getVendorPrice()
                        }
                );
            }
        }
    }
}
