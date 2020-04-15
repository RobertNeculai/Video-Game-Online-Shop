package org.fasttrackit.VideoGameOnlineShop.persistance;

import org.fasttrackit.VideoGameOnlineShop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
