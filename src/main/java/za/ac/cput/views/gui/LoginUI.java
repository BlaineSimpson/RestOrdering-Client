package za.ac.cput.views.gui;

import okhttp3.Credentials;
import za.ac.cput.util.Authenticate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {
    private JTextField textFieldUsername;
    private JPasswordField passwordFieldPassword;
    private JPanel loginPanel;
    private JButton loginButton;
    private String credentials;

    public LoginUI() {

    }

    public JPanel getLoginPanel(){
        loginRequest();
        return loginPanel;
    }

    public String getUsername(){
        return textFieldUsername.getText();
    }
    public String getPassword(){
        return String.valueOf(passwordFieldPassword.getPassword());
    }

    public String loginRequest(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Authenticate.getCredentials(getUsername(), getPassword());
                String username = getUsername();
                String password = getPassword();
                credentials = Credentials.basic(username, password);
                System.out.println(getUsername());
            }
        });
        return credentials;
    }
}
