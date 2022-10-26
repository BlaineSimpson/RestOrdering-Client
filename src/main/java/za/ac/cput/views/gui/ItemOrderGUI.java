package za.ac.cput.views.gui;

import za.ac.cput.domain.ItemOrder;
import za.ac.cput.views.requests.ItemOrderRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ItemOrderGUI {
    private JPanel itemOrderPanel;
    private JTextField textFieldItemId;
    private JTextField textFieldName;
    private JTextField textFieldNumberOfPlates;
    private JButton saveButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable itemOrderTable;
    private JLabel id;
    private JLabel name;
    private JLabel plate;
    private Font f1,f2;

    public JPanel getPanel(){
        itemOrderRequests();
        createTable();
        showTable();
        f1=new Font("Arial",Font.PLAIN,14);
        f2=new Font("Arial",Font.BOLD,14);
        deleteButton.setFont(f2);
        saveButton.setFont(f2);
        viewAllButton.setFont(f2);
        findButton.setFont(f2);
        id.setFont(f1);
        name.setFont(f1);
        plate.setFont(f1);
        return itemOrderPanel;
    }

    private void itemOrderRequests(){
        //save item order
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemOrderRequest.save(textFieldItemId.getText(), textFieldName.getText(), textFieldNumberOfPlates.getText());
                textFieldItemId.setText("");
                textFieldName.setText("");
                textFieldNumberOfPlates.setText("");
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
                ItemOrderRequest.viewById(textFieldItemId.getText());
                showTableById();
            }
        });
        //Delete admin
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemOrderRequest.delete(textFieldItemId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        itemOrderTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "Item Name"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) itemOrderTable.getModel();

        List itemOrderCreateList = ItemOrderRequest.getAll();
        List<ItemOrder> itemOrderList = itemOrderCreateList;
        model.setRowCount(0);
        for (int i = 0; i < itemOrderList.size(); i++){
            model.addRow(
                    new Object[] {
                            itemOrderList.get(i).getItemId(),
                            itemOrderList.get(i).getItemName()
                           // itemOrderList.get(i).getPrice(),
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) itemOrderTable.getModel();

        List itemOrderCreateList = ItemOrderRequest.getAll();
        List<ItemOrder> itemOrderList = itemOrderCreateList;
        model.setRowCount(0);
        for (ItemOrder itemOrder : itemOrderList) {
            if (itemOrder.getItemId().equals(textFieldItemId.getText())){
                model.addRow(
                        new Object[] {
                                itemOrder.getItemId(),
                                itemOrder.getItemName()
                               // itemOrder.getPrice()
                        }
                );
            }
        }
    }
}
