package org.fasttrackit.VideoGameOnlineShop.persistance;


import org.fasttrackit.VideoGameOnlineShop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserId(long id);

}
