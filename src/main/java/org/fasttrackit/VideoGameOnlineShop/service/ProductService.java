package org.fasttrackit.VideoGameOnlineShop.service;


import org.fasttrackit.VideoGameOnlineShop.domain.Product;
import org.fasttrackit.VideoGameOnlineShop.exception.ResourceNotFoundException;
import org.fasttrackit.VideoGameOnlineShop.persistance.ProductRepository;
import org.fasttrackit.VideoGameOnlineShop.transfer.product.GetProductsRequest;
import org.fasttrackit.VideoGameOnlineShop.transfer.product.ProductResponse;
import org.fasttrackit.VideoGameOnlineShop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(SaveProductRequest request) {
        LOGGER.info("Creating Product {}", request);
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setImageUrl(request.getImageUrl());
        product.setGenre(request.getGenre());
        product.setDiscount(request.getDiscount());

        Product savedProduct = productRepository.save(product);
        return mapProductResponse(savedProduct);
    }

    public ProductResponse getProduct(long id) {
        LOGGER.info("Retrieving product {}", id);
        Product product = findProduct(id);

        return mapProductResponse(product);

    }

    public Product findProduct(long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " + id + " not found"));
    }

    private ProductResponse mapProductResponse(Product product) {
        ProductResponse productDto = new ProductResponse();
        discountApplier(product);
        priceCalculator(product);
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setQuantity(product.getQuantity());
        productDto.setGenre(product.getGenre());
        productDto.setDiscount(product.getDiscount());
        productDto.setAverageRating(product.getAverageRating());
        return productDto;
    }

    private void discountApplier(Product product) {
        if(product.getDiscount().getStartDate()!=null)
        {
            if(LocalDateTime.now().isAfter(product.getDiscount().getStartDate()) && product.getDiscount().getStartDate().isBefore(product.getDiscount().getEndDate()) &&
                    LocalDateTime.now().isBefore(product.getDiscount().getEndDate())) {
                double price = product.getPrice();
                LOGGER.info("Product{} full price {}",product.getId(),product.getDiscount().getFullPrice());
                double v1 = product.getDiscount().getLevel() * price;
                double v =  v1/ 100;
                product.setPrice((price - v));
                
            }
        }
    }
    private void priceCalculator(Product product){
        if(product.getDiscount().getEndDate() != null)
            if(LocalDateTime.now().isAfter(product.getDiscount().getEndDate()) && product.getPrice()!=product.getDiscount().getFullPrice()) {
                LOGGER.info("Product{} reverting to original price {}",product.getId(),product.getDiscount().getFullPrice());
                product.setPrice(product.getDiscount().getFullPrice());
            }

    }

    public Page<ProductResponse> getProducts(GetProductsRequest request, Pageable pageable) {
        LOGGER.info("Retreving products that match the filter {}", request);
        Page<Product> productsPage;
        if (request != null) {
            if (request.getMinQuantity() != null && request.getPartialName() != null)
                productsPage = productRepository.findByNameContainingAndQuantityGreaterThanEqual(request.getPartialName(), request.getMinQuantity(), pageable);
            else if (request.getPartialName() != null)
                productsPage = productRepository.findByNameContaining(request.getPartialName(), pageable);
            else if (request.getGenre() != null)
                productsPage = productRepository.findByGenreContaining(request.getGenre(), pageable);
            else if (request.getDiscount()!=null)
                productsPage = productRepository.findByDiscount(request.getDiscount(), pageable);
            else if (request.getRating() != null)
                productsPage = productRepository.findByAverageRatingGreaterThanEqual(request.getRating(), pageable);
            else
                productsPage = productRepository.findAll(pageable);
        } else {

            productsPage = productRepository.findAll(pageable);
        }
        List<ProductResponse> productDtos = new ArrayList<>();
        for (Product product : productsPage.getContent()) {
            ProductResponse productDto = mapProductResponse(product);
            productDtos.add(productDto);
        }
        return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
    }

    public ProductResponse updateProduct(long id, SaveProductRequest request) {
        LOGGER.info("Updating product {}: {}", id, request);
        Product product = findProduct(id);
        BeanUtils.copyProperties(request, product);
        Product savedProduct = productRepository.save(product);
        return mapProductResponse(savedProduct);
    }

    public Page<ProductResponse> getTopRatedProducts(Pageable pageable) {
        LOGGER.info("Retreving top rated products");
        Page<Product> productsPage;
        productsPage = productRepository.findAll(pageable);
        List<ProductResponse> productDtos = new ArrayList<>();
        for (Product product : productsPage.getContent()) {
            priceCalculator(product);
            discountApplier(product);
            ProductResponse productDto = mapProductResponse(product);
            productDtos.add(productDto);
        }
        return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());
    }

    public void deleteProduct(long id) {
        LOGGER.info("Deleting product {}", id);
        productRepository.deleteById(id);
    }
}
