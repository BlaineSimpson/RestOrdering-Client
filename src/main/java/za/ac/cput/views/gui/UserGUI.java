package za.ac.cput.views.gui;

import za.ac.cput.domain.UserLogin;
import za.ac.cput.views.requests.UserRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserGUI {
    private JPanel userPanel;
    private JTextField textFieldUserId;
    private JTextField textFieldUsername;
    private JPasswordField passwordFieldUserPassword;
    private JButton saveUserButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable userTable;

    public JPanel getPanel(){
        userRequest();
        createTable();
        showTable();
        return userPanel;
    }

    private void userRequest(){
        //save user details
        saveUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRequest.save(textFieldUserId.getText(), textFieldUsername.getText(), String.valueOf(passwordFieldUserPassword.getPassword()));
                textFieldUserId.setText("");
                textFieldUsername.setText("");
                passwordFieldUserPassword.setText("");
                showTable();
            }
        });
        //View all users
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
                UserRequest.viewById(textFieldUserId.getText());
                showTableById();
            }
        });
        //Delete user
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRequest.delete(textFieldUserId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        userTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "UserName", "Password"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();

        List userCreateList = UserRequest.getAll();
        List<UserLogin> userList = userCreateList;
        model.setRowCount(0);
        for (int i = 0; i < userList.size(); i++){
            model.addRow(
                    new Object[] {
                            userList.get(i).getUserLoginId(),
                            userList.get(i).getUserLoginUsername(),
                            userList.get(i).getUserLoginPassword()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();

        model.setRowCount(0);

        List userCreateList = UserRequest.getAll();
        List<UserLogin> userList = userCreateList;

        for (UserLogin user : userList){
            if (user.getUserLoginId().equals(textFieldUserId.getText())){
                model.addRow(
                        new Object[] {
                                user.getUserLoginId(),
                                user.getUserLoginUsername(),
                                user.getUserLoginPassword()
                        }
                );
            }
        }
    }
}
