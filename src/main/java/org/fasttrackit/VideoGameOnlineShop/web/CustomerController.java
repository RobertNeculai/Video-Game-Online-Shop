package org.fasttrackit.VideoGameOnlineShop.web;

import org.fasttrackit.VideoGameOnlineShop.domain.Customer;
import org.fasttrackit.VideoGameOnlineShop.service.CustomerService;
import org.fasttrackit.VideoGameOnlineShop.transfer.customer.SaveCustomerRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.customer.UpdateCustomerRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.user.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody SaveCustomerRequest request) {
        Customer customer = customerService.createCustomer(request);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        Customer customer = customerService.getCustomer(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id,@RequestBody UpdateCustomerRequest request){
        Customer customer=customerService.updateCustomer(id,request);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
    @GetMapping("/userid/{id}")
    public ResponseEntity<Customer> getCustomerByUserId(@PathVariable long id){
        Customer customer=customerService.getCustomerByUser(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);

    }
}
