package za.ac.cput.util;

import okhttp3.Credentials;
import za.ac.cput.views.gui.LoginUI;

public class Authenticate {
    private static LoginUI loginUI = new LoginUI();
    public static String getCredentials(String username, String password){
        return Credentials.basic(username, password);
    }

    public static String setCredentials(){

        return getCredentials(loginUI.getUsername(), loginUI.getPassword());
    }
}
