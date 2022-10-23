package za.ac.cput.views.gui;

import za.ac.cput.domain.Restaurant;
import za.ac.cput.views.requests.AdminRequest;
import za.ac.cput.views.requests.RestaurantRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RestaurantGUI {
    private JPanel restaurantPanel;
    private JTextField textFieldRestaurantId;
    private JTextField textFieldName;
    private JTextField textFieldAddress;
    private JCheckBox checkIfOpenCheckBox;
    private JButton saveButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable restaurantTable;

    public JPanel getPanel(){
        restaurantRequests();
        createTable();
        showTable();
        return restaurantPanel;
    }

    private void restaurantRequests(){
        //save restaurant details
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantRequest.save(textFieldRestaurantId.getText(), textFieldName.getText(), textFieldAddress.getText(), checkIfOpenCheckBox.isSelected());
                textFieldRestaurantId.setText("");
                textFieldName.setText("");
                textFieldAddress.setText("");
                checkIfOpenCheckBox.setSelected(false);
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
                RestaurantRequest.viewById(textFieldRestaurantId.getText());
                showTableById();
            }
        });
        //Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestaurantRequest.delete(textFieldRestaurantId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        restaurantTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "Restaurant Name", "Address", "Open"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) restaurantTable.getModel();

        List restaurantCreateList = RestaurantRequest.getAll();
        List<Restaurant> restaurantList = restaurantCreateList;
        model.setRowCount(0);
        for (int i = 0; i < restaurantList.size(); i++){
            model.addRow(
                    new Object[] {
                            restaurantList.get(i).getRestaurantId(),
                            restaurantList.get(i).getRestaurantName(),
                            restaurantList.get(i).getRestaurantAddress(),
                            restaurantList.get(i).isOpen()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) restaurantTable.getModel();

        List restaurantCreateList = RestaurantRequest.getAll();
        List<Restaurant> restaurantList = restaurantCreateList;
        model.setRowCount(0);
        for (Restaurant restaurant : restaurantList){
            if (restaurant.getRestaurantId().equals(textFieldRestaurantId.getText())){
                model.addRow(
                        new Object[] {
                                restaurant.getRestaurantId(),
                                restaurant.getRestaurantName(),
                                restaurant.getRestaurantAddress(),
                                restaurant.isOpen()
                        }
                );
            }
        }
    }
}
