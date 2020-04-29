package org.fasttrackit.VideoGameOnlineShop.transfer.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.StringJoiner;

public class AddProductToCartRequest {
        @JsonIgnore
        private long customerId;
        private long productId;
        private int quantity;
        private double price;

        @Override
        public String toString() {
            return new StringJoiner(", ", org.fasttrackit.VideoGameOnlineShop.transfer.cart.AddProductsToCartRequest.class.getSimpleName() + "[", "]")
                    .add("customerId=" + customerId)
                    .add("productsId=" + productId)
                    .add("quantity="+quantity)
                    .add("price="+price)
                    .toString();
        }

        public long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(long customerId) {
            this.customerId = customerId;
        }

        public long getProductId() {
            return productId;
        }

        public void setProductId(long productId) {
            this.productId = productId;
        }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
