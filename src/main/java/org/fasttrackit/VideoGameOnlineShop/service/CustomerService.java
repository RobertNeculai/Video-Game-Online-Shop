package org.fasttrackit.VideoGameOnlineShop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.VideoGameOnlineShop.domain.Customer;
import org.fasttrackit.VideoGameOnlineShop.domain.User;
import org.fasttrackit.VideoGameOnlineShop.exception.ResourceNotFoundException;
import org.fasttrackit.VideoGameOnlineShop.persistance.CustomerRepository;
import org.fasttrackit.VideoGameOnlineShop.transfer.customer.SaveCustomerRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.customer.UpdateCustomerRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.user.UpdateUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService  {
    private static final Logger LOGGER = LoggerFactory.getLogger(org.fasttrackit.VideoGameOnlineShop.service.CustomerService.class);

    private final CustomerRepository customerRepository;
    private final UserService userService;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, UserService userService, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.userService = userService;
    }

    public Customer createCustomer(SaveCustomerRequest request) {
        LOGGER.info("Creating Customer {}", request);
        User user = userService.getUser(request.getUser_id());
        Customer customer=new Customer();
        customer.setAddress(request.getAddress());
        customer.setDob(request.getDob());
        customer.setEmail(request.getEmail());
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());

        customer.setUser(user);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) {
        LOGGER.info("Retrieving Customer {}", id);
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found"));
    }
    public Customer getCustomerByUser(long id){
        LOGGER.info("Retrieving Customer by UserId {}", id);
        return customerRepository.findByUserId(id)
                .orElseThrow(()->new ResourceNotFoundException(String.format("Customer with UserID {} not found",id)));
    }

    public Customer updateCustomer(long id, UpdateCustomerRequest request) {
        LOGGER.info("Updating Customer {}: {}", id, request);
        Customer customer = getCustomer(id);
        customer.setEmail(request.getEmail());
        customer.setAddress(request.getAddress());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        LOGGER.info("Deleting Customer {}", id);
        customerRepository.deleteById(id);
    }


}
