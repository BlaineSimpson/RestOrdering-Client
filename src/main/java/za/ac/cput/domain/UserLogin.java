/* UserLogin.java
 Entity for the User Login
 Author: Ali Mohamed (219113505)
 Date: 03 April 2022.
*/

package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class UserLogin implements Serializable {
    @Id
    @NotNull
    private String userLoginId;
    @NotNull
    private String userLoginUsername;
    @NotNull
    private String userLoginPassword;

    private UserLogin(Builder builder){
        this.userLoginId = builder.userLoginId;
        this.userLoginUsername = builder.userLoginUsername;
        this.userLoginPassword = builder.userLoginPassword;
    }

    protected UserLogin() {

    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public String getUserLoginUsername() {
        return userLoginUsername;
    }

    public String getUserLoginPassword() {
        return userLoginPassword;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userLoginId=" + userLoginId +
                ", userLoginUsername='" + userLoginUsername + '\'' +
                ", userLoginPassword='" + userLoginPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLogin userLogin = (UserLogin) o;
        return Objects.equals(userLoginId, userLogin.userLoginId) && Objects.equals(userLoginUsername, userLogin.userLoginUsername) && Objects.equals(userLoginPassword, userLogin.userLoginPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userLoginId, userLoginUsername, userLoginPassword);
    }

    public static class Builder{
        private String userLoginId;
        private String userLoginUsername;
        private String userLoginPassword;

        public Builder setUserLoginId(String userLoginId) {
            this.userLoginId = userLoginId;
            return this;
        }

        public Builder setUserLoginUsername(String userLoginUsername) {
            this.userLoginUsername = userLoginUsername;
            return this;
        }

        public Builder setUserLoginPassword(String userLoginPassword) {
            this.userLoginPassword = userLoginPassword;
            return this;
        }

        public Builder copy(UserLogin userLogin){
            this.userLoginId = userLogin.userLoginId;
            this.userLoginUsername = userLogin.userLoginUsername;
            this.userLoginPassword = userLogin.userLoginPassword;
            return this;
        }

        public UserLogin build(){
            return new UserLogin(this);
        }
    }
}
