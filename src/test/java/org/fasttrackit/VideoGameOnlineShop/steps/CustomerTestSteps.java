package org.fasttrackit.VideoGameOnlineShop.steps;

import org.fasttrackit.VideoGameOnlineShop.domain.Customer;
import org.fasttrackit.VideoGameOnlineShop.service.CustomerService;
import org.fasttrackit.VideoGameOnlineShop.transfer.customer.SaveCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@Component
public class CustomerTestSteps {
    @Autowired
    private CustomerService customerService;
    @Autowired
    public Customer createCustomer(){
        SaveCustomerRequest request=new SaveCustomerRequest();
        request.setFirstName("First Name");
        request.setLastName("Last Name");
        Customer customer = customerService.createCustomer(request);
        assertThat(customer,notNullValue());
        assertThat(customer.getId(),greaterThan(0L));
        assertThat(customer.getFirstName(),is(request.getFirstName()));
        assertThat(customer.getLastName(),is(request.getLastName()));
        return customer;
    }
}
