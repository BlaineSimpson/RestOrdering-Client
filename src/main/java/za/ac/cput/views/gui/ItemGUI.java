package za.ac.cput.views.gui;

import za.ac.cput.domain.Item;
import za.ac.cput.views.requests.ItemRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ItemGUI {
    private JPanel itemPanel;
    private JTextField textFieldItemId;
    private JTextField textFieldOrderId;
    private JTextField textFieldQuantity;
    private JTextField textFieldPrice;
    private JButton saveButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable itemTable;

    public JPanel getPanel(){
        itemRequests();
        createTable();
        showTable();
        return itemPanel;
    }

    private void itemRequests(){
        //save item details
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemRequest.save(textFieldItemId.getText(), textFieldOrderId.getText(), textFieldQuantity.getText(), textFieldPrice.getText());
                textFieldItemId.setText("");
                textFieldOrderId.setText("");
                textFieldQuantity.setText("");
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
                ItemRequest.viewById(textFieldItemId.getText());
                showTableById();
            }
        });
        //Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemRequest.delete(textFieldItemId.getText());
                showTable();
            }
        });
    }

    private void createTable(){
        itemTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "Order ID", "Quantity", "Price"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();

        List itemCreateList = ItemRequest.getAll();
        List<Item> itemList = itemCreateList;
        model.setRowCount(0);
        for (int i = 0; i < itemList.size(); i++){
            model.addRow(
                    new Object[] {
                            itemList.get(i).getID(),
                            itemList.get(i).getOrderID(),
                            itemList.get(i).getQuantity(),
                            itemList.get(i).getPrice()
                    }
            );
        }
    }

    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) itemTable.getModel();

        List itemCreateList = ItemRequest.getAll();
        List<Item> itemList = itemCreateList;
        model.setRowCount(0);
        for (Item item : itemList) {
            if (item.getID().equals(textFieldItemId.getText())){
                model.addRow(
                        new Object[] {
                                item.getID(),
                                item.getOrderID(),
                                item.getQuantity(),
                                item.getPrice()
                        }
                );
            }
        }
    }
}
