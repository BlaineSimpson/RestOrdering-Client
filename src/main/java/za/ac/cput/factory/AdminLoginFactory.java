/* AdminLoginFactory.java
 Entity for the creation of Admin Login
 Author: Ali Mohamed (219113505)
 Date: 03 April 2022.
*/

package za.ac.cput.factory;

import za.ac.cput.domain.AdminLogin;
import za.ac.cput.util.LoginHelper;

public class AdminLoginFactory {

    public static AdminLogin createAdmin(String adminLoginId, String adminLoginUsername, String adminLoginPassword){
        if (LoginHelper.isNull(adminLoginId) || LoginHelper.isNull(adminLoginUsername) || LoginHelper.isNull(adminLoginPassword))
            throw new IllegalArgumentException("Username and password cannot be empty or null");
//            return null;
//        if(LoginHelper.isNull(adminLoginId))
//            return null;
        return new AdminLogin.Builder().setAdminLoginId(adminLoginId)
                .setAdminLoginUserName(adminLoginUsername)
                .setAdminLoginPassword(adminLoginPassword)
                .build();
    }
}
