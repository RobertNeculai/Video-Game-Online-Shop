package org.fasttrackit.VideoGameOnlineShop.service;

import org.fasttrackit.VideoGameOnlineShop.config.ApplicationUser;
import org.fasttrackit.VideoGameOnlineShop.domain.User;
import org.fasttrackit.VideoGameOnlineShop.persistance.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

        return new ApplicationUser(user);
    }
}
