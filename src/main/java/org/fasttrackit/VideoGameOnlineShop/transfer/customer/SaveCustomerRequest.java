package org.fasttrackit.VideoGameOnlineShop.transfer.customer;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class SaveCustomerRequest {
        @NotNull
        private String firstName;
        @NotNull
        private String lastName;

    @Override
    public String toString() {
        return new StringJoiner(", ", SaveCustomerRequest.class.getSimpleName() + "[", "]")
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
