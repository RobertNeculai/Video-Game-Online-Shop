package org.fasttrackit.VideoGameOnlineShop.persistance;

import org.fasttrackit.VideoGameOnlineShop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findUserByUsername(String username);
    User findById(long id);

}
