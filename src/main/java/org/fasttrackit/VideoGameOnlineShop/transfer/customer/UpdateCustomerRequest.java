package org.fasttrackit.VideoGameOnlineShop.transfer.customer;
import javax.validation.constraints.NotNull;

import java.util.StringJoiner;

public class UpdateCustomerRequest {
    @NotNull
    private String email;
    @NotNull
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", org.fasttrackit.VideoGameOnlineShop.transfer.customer.SaveCustomerRequest.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .add("address='" + address + "'")
                .toString();
    }
}
