package org.fasttrackit.VideoGameOnlineShop;

import org.fasttrackit.VideoGameOnlineShop.domain.Customer;
import org.fasttrackit.VideoGameOnlineShop.domain.Product;
import org.fasttrackit.VideoGameOnlineShop.service.CartService;
import org.fasttrackit.VideoGameOnlineShop.steps.CustomerTestSteps;
import org.fasttrackit.VideoGameOnlineShop.steps.ProductTestSteps;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.CartResponse;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.ProductInCartResponse;
import org.fasttrackit.VideoGameOnlineShop.transfer.product.ProductResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CartServiceIntegrationTest {
    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerTestSteps customerTestSteps;
    @Autowired
    private ProductTestSteps productTestSteps;
    @Test
    void addProductsToCart_whenNewCart_thenCartIsCreated(){
        Customer customer= customerTestSteps.createCustomer();
        ProductResponse product = productTestSteps.createProduct();

        AddProductsToCartRequest cartRequest=new AddProductsToCartRequest();
        cartRequest.setCustomerId(customer.getId());
        cartRequest.setProductsIds(Collections.singletonList(product.getId()));

        cartService.addProductsToCart(cartRequest);
        CartResponse cart = cartService.getCart(customer.getId());
        assertThat(cart,notNullValue());
        assertThat(cart.getId(),is(customer.getId()));
        assertThat(cart.getProducts(),notNullValue());
        assertThat(cart.getProducts(),hasSize(1));

        Iterator<ProductInCartResponse> productIterator = cart.getProducts().iterator();
        assertThat(productIterator.hasNext(),is(true));
        ProductInCartResponse nextProduct = productIterator.next();
        assertThat(nextProduct,notNullValue());
        assertThat(nextProduct.getId(),is(product.getId()));
        assertThat(nextProduct.getName(),is(product.getName()));
        assertThat(nextProduct.getPrice(),is(product.getPrice()));
    }
    @Test
    void deleteProductFromCart_whenOnlyOneProductInCart_thenDeleteCart(){
        Customer customer=customerTestSteps.createCustomer();
        ProductResponse product=productTestSteps.createProduct();
        AddProductToCartRequest request=new AddProductToCartRequest();
        request.setCustomerId(customer.getId());
        request.setProductId(product.getId());
        request.setQuantity(1);
        request.setPrice(100);
        cartService.addProductToCart(request);
        CartResponse cart=cartService.getCart(customer.getId());
        assertThat(cart,notNullValue());
        assertThat(cart.getId(),is(customer.getId()));
        assertThat(cart.getProducts(),notNullValue());
        assertThat(cart.getProducts(),hasSize(1));
        AddProductsToCartRequest deleteRequest=new AddProductsToCartRequest();
        deleteRequest.setCustomerId(request.getCustomerId());
        List<Long> list = Collections.singletonList(request.getProductId());
        deleteRequest.setProductsIds(list);
        cartService.removeProductsFromCart(deleteRequest);
        CartResponse cart2=cartService.getCart(request.getCustomerId());
        Iterator<ProductInCartResponse> productIterator = cart2.getProducts().iterator();
        assertThat(productIterator.hasNext(),is(true));
        ProductInCartResponse nextProduct = productIterator.next();
        assertThat(nextProduct,notNullValue());
        assertThat(nextProduct.getId(),is(product.getId()));
        assertThat(cart2,is(null));




    }
//    @Test
//    void getProductsFromCart_
}
