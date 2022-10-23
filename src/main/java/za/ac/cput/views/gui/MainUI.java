package za.ac.cput.views.gui;

import javax.swing.*;
import java.awt.*;

public class MainUI {
    private JPanel rootPanel;

    private JTabbedPane tabbedPaneCategoryAdmin;
    private JTabbedPane tabbedPaneCategoryUser;
    private JTabbedPane tabbedPaneRoles;
    private JPanel adminPanel;
    private JPanel userPanel;
    private JPanel billPanel;
    private JPanel customerPanel;
    private JPanel inventoryPanel;
    private JPanel itemOrderPanel;
    private JPanel itemPanel;
    private JPanel menuPanel;
    private JPanel orderPanel;

    public JPanel getRootPanel(){
        tab();
        return rootPanel;
    }
    public void tab(){
        //create panels from forms
        adminPanel = new AdminGUI().getPanel();
        userPanel = new UserGUI().getPanel();
        billPanel = new BillGUI().getPanel();
        customerPanel = new CustomerGUI().getPanel();
        inventoryPanel = new InventoryGUI().getPanel();
        itemOrderPanel = new ItemOrderGUI().getPanel();
        itemPanel = new ItemGUI().getPanel();
        menuPanel = new MenuGUI().getPanel();
        orderPanel = new OrderGUI().getPanel();

        //tabbedPaneRoles.setPreferredSize(new Dimension(800, 500));

        //Security tab panel
        tabbedPaneRoles.add(tabbedPaneCategoryAdmin, "Admin");
        tabbedPaneRoles.add(tabbedPaneCategoryUser, "User");

        //Domain tab panels
        tabbedPaneCategoryAdmin.add(adminPanel, "Admin Account");
        tabbedPaneCategoryAdmin.add(userPanel, "User Account");
        tabbedPaneCategoryAdmin.add(customerPanel, "Customer");
        tabbedPaneCategoryAdmin.add(inventoryPanel, "Inventory");

        tabbedPaneCategoryUser.add(billPanel, "Bill");
        tabbedPaneCategoryUser.add(itemOrderPanel, "Item Order");
        tabbedPaneCategoryUser.add(itemPanel, "Item");
        tabbedPaneCategoryUser.add(menuPanel, "Menu");
        tabbedPaneCategoryUser.add(orderPanel, "Order");
    }
}
