package org.fasttrackit.VideoGameOnlineShop.persistance;


import org.fasttrackit.VideoGameOnlineShop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
