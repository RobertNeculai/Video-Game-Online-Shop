package org.fasttrackit.VideoGameOnlineShop.web;

import org.fasttrackit.VideoGameOnlineShop.service.CartService;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.cart.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RequestMapping("/carts")
@RestController
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    //Post means create... we're doing createOrUpdate => PUT
    @PutMapping
    public ResponseEntity<Void> addProductToCart(@Valid@RequestBody AddProductsToCartRequest request){
        cartService.addProductsToCart(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart(@PathVariable("id") long customerId){
        CartResponse cart = cartService.getCart(customerId);
        return new ResponseEntity<>(cart,HttpStatus.OK);
    }

}
