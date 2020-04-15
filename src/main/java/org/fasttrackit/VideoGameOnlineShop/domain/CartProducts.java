package org.fasttrackit.VideoGameOnlineShop.domain;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart_product")
public class CartProducts {
    @EmbeddedId
    private CartProductsId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cartId")
    private Cart cart;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    public CartProducts() {
    }

    public CartProducts(Cart cart, Product product) {
        this.cart = cart;
        this.product = product;
        this.id = new CartProductsId(cart.getId(), product.getId());

    }

    public CartProductsId getId() {
        return id;
    }

    public void setId(CartProductsId id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartProducts that = (CartProducts) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(cart, that.cart)) return false;
        return Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }
}
