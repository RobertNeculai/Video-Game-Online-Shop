package org.fasttrackit.VideoGameOnlineShop.transfer.product;

import javax.validation.constraints.NotNull;
import java.util.StringJoiner;

public class SaveProductRequest {
    private String description;
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;
    private String imageUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SaveProductRequest.class.getSimpleName() + "[", "]")
                .add("description='" + description + "'")
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("quantity=" + quantity)
                .add("imageUrl='" + imageUrl + "'")
                .add("genre='" + genre + "'")
                .toString();
    }
}
