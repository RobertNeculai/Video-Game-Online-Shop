package org.fasttrackit.VideoGameOnlineShop.transfer.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class UpdateUserRequest {

    @NotEmpty
    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", org.fasttrackit.VideoGameOnlineShop.domain.User.class.getSimpleName() + "[", "]")
                .add("password='" + password + "'")
                .toString();
    }
}
