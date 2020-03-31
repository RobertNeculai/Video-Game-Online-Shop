package org.fasttrackit.VideoGameOnlineShop.transfer.product;

import java.util.StringJoiner;

public class GetProductsRequest {
    private String partialName;
    private Integer minQuantity;
    private String genre;
    private boolean discount;

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GetProductsRequest.class.getSimpleName() + "[", "]")
                .add("partialName='" + partialName + "'")
                .add("minQuantity=" + minQuantity)
                .add("genre='" + genre + "'")
                .toString();
    }
}
