package org.fasttrackit.VideoGameOnlineShop.service;

import org.fasttrackit.VideoGameOnlineShop.auth.ApplicationUser;
import org.fasttrackit.VideoGameOnlineShop.domain.User;
import org.fasttrackit.VideoGameOnlineShop.persistance.UserRepository;
import org.fasttrackit.VideoGameOnlineShop.transfer.user.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class UserService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(org.fasttrackit.VideoGameOnlineShop.service.CustomerService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User registerUser(SaveUserRequest request) {
        LOGGER.info("Creating User {}", request);
        User user=new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }
    public User updateUser(String username, SaveUserRequest request) {
        LOGGER.info("Updating User {}: {}", username, request);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
        BeanUtils.copyProperties(request, user);
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        LOGGER.info("Deleting User {}", id);
        userRepository.deleteById(id);
    }
    public User getUser(long id){
       return userRepository.findById(id);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
        return new ApplicationUser(user);
    }
}