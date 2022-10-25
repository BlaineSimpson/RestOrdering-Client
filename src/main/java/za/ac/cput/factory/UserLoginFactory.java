/* UserLoginFactory.java
 Entity for the creation of User Login
 Author: Ali Mohamed (219113505)
 Date: 03 April 2022.
*/

package za.ac.cput.factory;

import za.ac.cput.domain.UserLogin;
import za.ac.cput.util.LoginHelper;

public class UserLoginFactory {

    public static UserLogin createUser(String userLoginId, String userLoginUsername, String userLoginPassword){
        if (LoginHelper.isNull(userLoginId) || LoginHelper.isNull(userLoginUsername) || LoginHelper.isNull(userLoginPassword))
            throw new IllegalArgumentException("ID, Username and password cannot be empty or null");
//        if (LoginHelper.invalidId(userLoginId))
//            return null;

        return new UserLogin.Builder().setUserLoginId(userLoginId)
                .setUserLoginUsername(userLoginUsername)
                .setUserLoginPassword(userLoginPassword)
                .build();
    }
}
