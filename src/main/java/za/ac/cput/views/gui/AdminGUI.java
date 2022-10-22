package za.ac.cput.views.gui;

import za.ac.cput.domain.AdminLogin;
import za.ac.cput.views.requests.AdminRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminGUI {
    private JPanel adminPanel;
    private JTextField textFieldAdminId;
    private JPasswordField passwordFieldAdminPassword;
    private JTextField textFieldAdminUsername;
    private JTable adminTable;
    private JButton save;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;

    public JPanel getPanel(){
        adminRequests();
        return adminPanel;
    }

    private void adminRequests() {
        //save admin details
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRequest.save(textFieldAdminId.getText(), textFieldAdminUsername.getText(), String.valueOf(passwordFieldAdminPassword.getPassword()));
                textFieldAdminId.setText("");
                textFieldAdminUsername.setText("");
                passwordFieldAdminPassword.setText("");
                showTable();
            }
        });
        //View all admin
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
                AdminRequest.viewById(textFieldAdminId.getText());
                showTableById();
            }
        });
        //Delete admin
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminRequest.deleteAdmin(textFieldAdminId.getText());
                showTable();
            }
        });
    }

    private void createTable(){
        adminTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "UserName", "Password"}
        ));

        //Set column width
//        TableColumnModel columns = adminTable.getColumnModel();
//        columns.getColumn(0).setMinWidth(20);
//        //Center data in table
//        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
//        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
//        columns.getColumn(0).setCellRenderer(cellRenderer);
//        columns.getColumn(1).setCellRenderer(cellRenderer);
//        columns.getColumn(2).setCellRenderer(cellRenderer);
//        columns.getColumn(3).setCellRenderer(cellRenderer);

    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) adminTable.getModel();

        List adminCreateList = AdminRequest.getAll();
        List<AdminLogin> adminList = adminCreateList;
        model.setRowCount(0);
        for (int i = 0; i < adminList.size(); i++){
            model.addRow(
                    new Object[] {
                            adminList.get(i).getAdminLoginId(),
                            adminList.get(i).getAdminLoginUsername(),
                            adminList.get(i).getAdminLoginPassword()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) adminTable.getModel();

        //AdminRequests.viewById(textFieldAdminId.getText());
        model.setRowCount(0);

        List adminCreateList = AdminRequest.getAll();
        List<AdminLogin> adminList = adminCreateList;

        for (AdminLogin admin : adminList){
            if (admin.getAdminLoginId().equals(textFieldAdminId.getText())){
                model.addRow(
                        new Object[] {
                                admin.getAdminLoginId(),
                                admin.getAdminLoginUsername(),
                                admin.getAdminLoginPassword()
                        }
                );
            }
        }
    }
}
