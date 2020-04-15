package org.fasttrackit.VideoGameOnlineShop.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Cart {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartProducts> products = new HashSet<>();


    public void addProductToCart(Product product) {
        boolean productNotPresent = true;

        for (CartProducts cartProducts : products) {
            if (cartProducts.getCart().equals(this) && cartProducts.getProduct().equals(product)) {
                cartProducts.setQuantity(cartProducts.getQuantity() + 1);
                productNotPresent = false;
                break;
            }
        }

        if (productNotPresent) {
            CartProducts cartProducts = new CartProducts(this, product);
            cartProducts.setQuantity(1);
            products.add(cartProducts);
        }
    }

    public void deleteProductFromCart(Product product) {
        for (Iterator<CartProducts> iterator = products.iterator();
             iterator.hasNext(); ) {
            CartProducts cartProducts = iterator.next();

            if (cartProducts.getCart().equals(this) &&
                    cartProducts.getProduct().equals(product)) {
                iterator.remove();
                cartProducts.setCart(null);
                cartProducts.setProduct(null);
            }
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<CartProducts> getProducts() {
        return products;
    }

    public void setProducts(Set<CartProducts> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;

        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
