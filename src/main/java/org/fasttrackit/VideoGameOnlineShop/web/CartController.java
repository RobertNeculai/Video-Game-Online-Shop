package org.fasttrackit.VideoGameOnlineShop.web;

import org.fasttrackit.VideoGameOnlineShop.service.CartService;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.AddProductToCartRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin
@RequestMapping("/carts")
@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping
    public ResponseEntity<Void> addProductToCart(@Valid @RequestBody AddProductsToCartRequest request) {
        cartService.addProductsToCart(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/SingleProductAddition {id}")
    public ResponseEntity<Void> addProductToCart(@PathVariable("id") long customerId,@Valid @RequestBody AddProductToCartRequest request) {
        request.setCustomerId(customerId);
        cartService.addProductToCart(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("id") long customerId) {
        CartResponse cart = cartService.getCart(customerId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProductFromCart(@Valid @RequestBody AddProductsToCartRequest request) {
        cartService.removeProductsFromCart(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    private void setAccesControlHeaders(HttpServletResponse resp)
    {
        // CORS configuration ( cross origin resource sharing)
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Methods","GET, POST, PUT, DELETE");
        resp.setHeader("Access-Control-Allow-Headers","content-type");
    }
}
