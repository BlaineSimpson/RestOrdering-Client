/* AdminLogin.java
 Entity for the Admin Login
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
public class AdminLogin implements Serializable {
    @NotNull
    @Id
    private String adminLoginId;
    @NotNull
    private String adminLoginUsername;
    @NotNull
    private String adminLoginPassword;

    private AdminLogin(Builder builder){
        this.adminLoginId = builder.adminLoginId;
        this.adminLoginUsername = builder.adminLoginUserName;
        this.adminLoginPassword = builder.adminLoginPassword;
    }

    protected AdminLogin() {

    }

    public String getAdminLoginId() {
        return adminLoginId;
    }

    public String getAdminLoginUsername() {
        return adminLoginUsername;
    }

    public String getAdminLoginPassword() {
        return adminLoginPassword;
    }

    @Override
    public String toString() {
        return "AdminLogin{" +
                "adminLoginId=" + adminLoginId +
                ", adminLoginUserName='" + adminLoginUsername + '\'' +
                ", adminLoginPassword='" + adminLoginPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminLogin that = (AdminLogin) o;
        return Objects.equals(adminLoginId, that.adminLoginId) && Objects.equals(adminLoginUsername, that.adminLoginUsername) && Objects.equals(adminLoginPassword, that.adminLoginPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminLoginId, adminLoginUsername, adminLoginPassword);
    }

    public static class Builder{
        private String adminLoginId;
        private String adminLoginUserName;
        private String adminLoginPassword;

        public Builder setAdminLoginId(String adminLoginId) {
            this.adminLoginId = adminLoginId;
            return this;
        }

        public Builder setAdminLoginUserName(String adminLoginUserName) {
            this.adminLoginUserName = adminLoginUserName;
            return this;
        }

        public Builder setAdminLoginPassword(String adminLoginPassword) {
            this.adminLoginPassword = adminLoginPassword;
            return this;
        }

        public Builder copy(AdminLogin adminLogin){
            this.adminLoginId = adminLogin.adminLoginId;
            this.adminLoginUserName = adminLogin.adminLoginUsername;
            this.adminLoginPassword = adminLogin.adminLoginPassword;
            return this;
        }

        public AdminLogin build(){
            return new AdminLogin(this);
        }
    }
}
