package org.fasttrackit.VideoGameOnlineShop.service;

import org.fasttrackit.VideoGameOnlineShop.domain.Cart;
import org.fasttrackit.VideoGameOnlineShop.domain.CartProducts;
import org.fasttrackit.VideoGameOnlineShop.domain.Customer;
import org.fasttrackit.VideoGameOnlineShop.domain.Product;
import org.fasttrackit.VideoGameOnlineShop.exception.ResourceNotFoundException;
import org.fasttrackit.VideoGameOnlineShop.persistance.CartRepository;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.CartResponse;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.ProductInCartResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class CartService {
    Logger LOGGER = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerService customerService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Transactional
    public void addProductsToCart(AddProductsToCartRequest request) {
        LOGGER.info("Adding products to cart: {}", request);
        Cart cart = cartRepository.findById(request.getCustomerId()).orElse(new Cart());
        if (cart.getCustomer() == null) {
            Customer customer = customerService.getCustomer(request.getCustomerId());
            cart.setCustomer(customer);
        }
        for (Long id : request.getProductsIds()) {
            Product product = productService.findProduct(id);
            cart.addProductToCart(product);
        }
        cartRepository.save(cart);
    }

    @Transactional
    public CartResponse getCart(long customerId) {
        LOGGER.info("Retrieving cart items for customer {}", customerId);
        Cart cart = cartRepository.findById(customerId).orElseThrow(() -> new ResourceNotFoundException("Cart " + customerId + " does not exist"));


        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());
        Set<ProductInCartResponse> productDtos = new HashSet<>();
        for (CartProducts nextProduct : cart.getProducts()) {
            ProductInCartResponse productDto = new ProductInCartResponse();
            productDto.setId(nextProduct.getProduct().getId());
            productDto.setName(nextProduct.getProduct().getName());
            productDto.setPrice(nextProduct.getProduct().getPrice());
            productDtos.add(productDto);
        }
        cartResponse.setProducts(productDtos);
        return cartResponse;
    }

    @Transactional
    public void removeProductsFromCart(AddProductsToCartRequest request) {
        LOGGER.info("Removing products from cart: {}", request);
        Cart cart = cartRepository.findById(request.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Cart " + request.getCustomerId() + " does not exist"));
        for (Long id : request.getProductsIds()) {
            Product product = productService.findProduct(id);
            cart.deleteProductFromCart(product);
        }
        cartRepository.save(cart);
    }
}
