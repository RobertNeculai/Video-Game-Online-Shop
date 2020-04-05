package org.fasttrackit.VideoGameOnlineShop.domain;

import org.fasttrackit.VideoGameOnlineShop.transfer.product.ProductResponse;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @Range(min=0,max = 5)
    private int rating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
            this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Review.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("content='" + content + "'")
                .add("rating='" + rating + "'")
                .toString();
    }
}
