package org.fasttrackit.VideoGameOnlineShop.transfer.product;

import java.util.StringJoiner;

public class ProductResponse {

    private long id;
    private String description;
    private String name;
    private double price;
    private int quantity;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
        return new StringJoiner(", ", ProductResponse.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("description='" + description + "'")
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("quantity=" + quantity)
                .add("imageUrl='" + imageUrl + "'")
                .add("genre='" + genre + "'")
                .toString();
    }
}
