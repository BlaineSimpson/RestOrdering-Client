package za.ac.cput.views.gui;

import za.ac.cput.domain.Menu;
import za.ac.cput.views.requests.MenuRequest;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuGUI {
    private JTextField textFieldMenuId;
    private JTextField textFieldType;
    private JPanel menuPanel;
    private JButton saveButton;
    private JButton viewAllButton;
    private JButton findButton;
    private JButton deleteButton;
    private JTable menuTable;
    private JLabel id;
    private JLabel type;
    private Font f1,f2;

    public JPanel getPanel(){
        menuRequests();
        createTable();
        showTable();
        f1=new Font("Arial",Font.PLAIN,14);
        f2=new Font("Arial",Font.BOLD,14);
        saveButton.setFont(f2);
        findButton.setFont(f2);
        viewAllButton.setFont(f2);
        deleteButton.setFont(f2);
        id.setFont(f1);
        type.setFont(f1);
        return menuPanel;
    }

    private void menuRequests(){
        //save menu details
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuRequest.save(textFieldMenuId.getText(), textFieldType.getText());
                textFieldMenuId.setText("");
                textFieldType.setText("");
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
                MenuRequest.viewById(textFieldMenuId.getText());
                showTableById();
            }
        });
        //Delete admin
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuRequest.delete(textFieldMenuId.getText());
                showTable();
            }
        });
    }
    private void createTable(){
        menuTable.setModel(new DefaultTableModel(
                null,
                new String[] {"ID", "Type"}
        ));
    }
    private void showTable(){
        DefaultTableModel model = (DefaultTableModel) menuTable.getModel();

        List menuCreateList = MenuRequest.getAll();
        List<Menu> menuList = menuCreateList;
        model.setRowCount(0);
        for (int i = 0; i < menuList.size(); i++){
            model.addRow(
                    new Object[] {
                            menuList.get(i).getMenuId(),
                            menuList.get(i).getMenuT()
                    }
            );
        }
    }
    private void showTableById(){
        DefaultTableModel model = (DefaultTableModel) menuTable.getModel();

        List menuCreateList = MenuRequest.getAll();
        List<Menu> menuList = menuCreateList;
        model.setRowCount(0);
        for (Menu menu : menuList) {
            if (menu.getMenuId().equals(textFieldMenuId.getText())){
                model.addRow(
                        new Object[] {
                                menu.getMenuId(),
                                menu.getMenuT()
                        }
                );
            }
        }
    }
}
