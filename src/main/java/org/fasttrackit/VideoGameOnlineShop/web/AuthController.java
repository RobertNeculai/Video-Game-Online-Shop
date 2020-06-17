package org.fasttrackit.VideoGameOnlineShop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin
@Controller
@RequestMapping("/")
public class AuthController {
    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }
    @GetMapping("shop")
    public String getProducts() {
        return "shop";
    }

    @GetMapping("register")
    public String getRegisterView(){
        return "register";
    }
}