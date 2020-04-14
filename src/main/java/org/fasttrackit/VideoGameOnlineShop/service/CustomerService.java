package org.fasttrackit.VideoGameOnlineShop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.VideoGameOnlineShop.domain.Customer;
import org.fasttrackit.VideoGameOnlineShop.exception.ResourceNotFoundException;
import org.fasttrackit.VideoGameOnlineShop.persistance.CustomerRepository;
import org.fasttrackit.VideoGameOnlineShop.transfer.customer.SaveCustomerRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(org.fasttrackit.VideoGameOnlineShop.service.CustomerService.class);

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    public Customer createCustomer(SaveCustomerRequest request) {
        LOGGER.info("Creating Customer {}", request);
        Customer customer = objectMapper.convertValue(request,Customer.class);
        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) {
        LOGGER.info("Retrieving Customer {}", id);
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer " + id + " not found"));
    }

    //
    public Customer updateCustomer(long id, SaveProductRequest request) {
        LOGGER.info("Updating product {}: {}", id, request);
        Customer customer = getCustomer(id);
        BeanUtils.copyProperties(request, customer);
        return customerRepository.save(customer);
    }

    public void deleteCustomer(long id) {
        LOGGER.info("Deleting Customer {}", id);
        customerRepository.deleteById(id);
    }


}
