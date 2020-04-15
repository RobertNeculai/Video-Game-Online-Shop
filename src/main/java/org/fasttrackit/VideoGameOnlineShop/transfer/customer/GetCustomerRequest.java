package org.fasttrackit.VideoGameOnlineShop.transfer.customer;

import java.util.StringJoiner;

public class GetCustomerRequest {

    private String partialFirstName;
    private String partialLastName;

    public String getPartialFirstName() {
        return partialFirstName;
    }

    public void setPartialFirstName(String partialFirstName) {
        this.partialFirstName = partialFirstName;
    }

    public String getPartialLastName() {
        return partialLastName;
    }

    public void setPartialLastName(String partialLastName) {
        this.partialLastName = partialLastName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetCustomerRequest.class.getSimpleName() + "[", "]")
                .add("partialFirstName='" + partialFirstName + "'")
                .add("partialLastName='" + partialLastName + "'")
                .toString();
    }
}
