package org.fasttrackit.VideoGameOnlineShop.web;

import org.fasttrackit.VideoGameOnlineShop.domain.User;
import org.fasttrackit.VideoGameOnlineShop.service.UserService;
import org.fasttrackit.VideoGameOnlineShop.transfer.user.SaveUserRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @ResponseBody
    public Long currentLoggedUserNameId(Principal principal) {
        UserResponse user = userService.getUserBySession(principal.getName());
        return user.getId();
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody SaveUserRequest request) {
        User user = userService.registerUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateCustomer(@PathVariable String username, @RequestBody SaveUserRequest request) {
        User user = userService.updateUser(username, request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
