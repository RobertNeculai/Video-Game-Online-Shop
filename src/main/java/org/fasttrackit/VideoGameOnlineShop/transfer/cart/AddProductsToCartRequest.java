package org.fasttrackit.VideoGameOnlineShop.transfer.cart;

import java.util.List;
import java.util.StringJoiner;

public class AddProductsToCartRequest {

    private long customerId;
    private List<Long> productsIds;

    @Override
    public String toString() {
        return new StringJoiner(", ", AddProductsToCartRequest.class.getSimpleName() + "[", "]")
                .add("customerId=" + customerId)
                .add("productsIds=" + productsIds)
                .toString();
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
