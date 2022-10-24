package za.ac.cput.views.gui;

import za.ac.cput.domain.Orderr;
import za.ac.cput.views.requests.AdminRequest;
import za.ac.cput.views.requests.OrderrRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class OrderGUI {
    private JTextField textFieldOrderId;
    private JTextField textFieldDate;
    private JCheckBox checkIfReadyCheckBox;
    private JButton saveButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JPanel orderPanel;
    private JTable orderTable;
    private JLabel id;
    private JLabel date;
    private JLabel ready;
    private Font f1,f2;

    public JPanel getPanel(){
        orderRequests();
        createTable();
        showTable();
        f1=new Font("Arial",Font.PLAIN,14);
        f2=new Font("Arial",Font.BOLD,14);
        viewAllButton.setFont(f2);
        deleteButton.setFont(f2);
        saveButton.setFont(f2);
        findButton.setFont(f2);
        ready.setFont(f1);
        id.setFont(f1);
        date.setFont(f1);
        return orderPanel;
    }

    private void orderRequests(){
        //save order details
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderrRequest.save(textFieldOrderId.getText(), textFieldDate.getText(), checkIfReadyCheckBox.isSelected());
                textFieldOrderId.setText("");
                textFieldDate.setText("");
                checkIfReadyCheckBox.setSelected(false);
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
                OrderrRequest.viewById(textFieldOrderId.getText());
                showTableById();
            }
        });
        //Delete admin
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderrRequest.delete(textFieldOrderId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        orderTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "Date", "Ready"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();

        List orderCreateList = OrderrRequest.getAll();
        List<Orderr> orderList = orderCreateList;
        model.setRowCount(0);
        for (int i = 0; i < orderList.size(); i++){
            model.addRow(
                    new Object[] {
                            orderList.get(i).getOrderID(),
                            orderList.get(i).getOrderDate(),
                            orderList.get(i).isReady()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();

        List orderCreateList = OrderrRequest.getAll();
        List<Orderr> orderList = orderCreateList;
        model.setRowCount(0);

        for (Orderr orderr : orderList) {
            if (orderr.getOrderID().equals(textFieldOrderId.getText())){
                model.addRow(
                        new Object[] {
                                orderr.getOrderID(),
                                orderr.getOrderDate(),
                                orderr.isReady()
                        }
                );
            }
        }
    }
}
