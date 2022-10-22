package za.ac.cput.views.gui;

import javax.swing.*;

public class MainUI {
    private JPanel rootPanel;

    private JTabbedPane tabbedPaneCategory;
    private JTabbedPane tabbedPaneRoles;
    private JPanel adminPanel;
    private JPanel userPanel;

    public JPanel getRootPanel(){
        tab();
        return rootPanel;
    }
    public void tab(){
        adminPanel = new AdminGUI().getPanel();
        userPanel = new UserGUI().getPanel();
        tabbedPaneRoles.add(tabbedPaneCategory, "Admin");
        tabbedPaneCategory.add(adminPanel, "Admin Account");
        tabbedPaneCategory.add(userPanel, "User Account");
    }
}
