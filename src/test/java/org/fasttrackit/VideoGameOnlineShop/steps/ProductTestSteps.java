package org.fasttrackit.VideoGameOnlineShop.steps;

import org.fasttrackit.VideoGameOnlineShop.domain.Discount;
import org.fasttrackit.VideoGameOnlineShop.service.ProductService;
import org.fasttrackit.VideoGameOnlineShop.transfer.product.ProductResponse;
import org.fasttrackit.VideoGameOnlineShop.transfer.product.SaveProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@Component
public class ProductTestSteps {
    @Autowired
    private ProductService productService;

    public ProductResponse createProduct() {
        SaveProductRequest request = new SaveProductRequest();
        request.setName("Phone");
        request.setQuantity(100);
        request.setPrice(300.5);
        Discount discount=new Discount();
        discount.setLevel(0);
        discount.setStartDate(LocalDateTime.now());
        discount.setEndDate(LocalDateTime.now().plusMinutes(1));
        request.setDiscount(discount);
        ProductResponse product = productService.createProduct(request);
        assertThat(product, notNullValue());
        assertThat(product.getId(), greaterThan(0L));
        assertThat(product.getName(), is(request.getName()));
        assertThat(product.getPrice(), is(request.getPrice()));
        assertThat(product.getQuantity(), is(request.getQuantity()));
        return product;
    }
}
