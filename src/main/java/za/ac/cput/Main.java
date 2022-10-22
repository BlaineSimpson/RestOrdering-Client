package za.ac.cput;

import za.ac.cput.views.gui.MainUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    private static void createGUI(){
        MainUI ui = new MainUI();
        JPanel root = ui.getRootPanel();

        //JFrame settings
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        //frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createGuiComponents(){

    }
}
