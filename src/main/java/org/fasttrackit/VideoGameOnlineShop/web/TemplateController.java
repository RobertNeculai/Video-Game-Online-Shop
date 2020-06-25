package org.fasttrackit.VideoGameOnlineShop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin
@Controller
@RequestMapping("/")
public class TemplateController {
    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }
    @GetMapping("shop")
    public String getProducts() {
        return "shop";
    }

    @GetMapping("UserRegister")
    public String getUserRegisterView(){
        return "UserRegister";
    }
    @GetMapping("CustomerRegister")
    public String getCustomerRegisterView(){
        return "CustomerRegister";
    }


    @GetMapping("CartPage")
    public String getCartPageView(){
        return "CartPage";
    }

    @GetMapping("single-product")
    public String getSingleProductPageView(){
        return "single-product";
    }
}