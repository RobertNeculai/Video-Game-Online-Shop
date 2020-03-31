package org.fasttrackit.VideoGameOnlineShop.service;

import org.fasttrackit.VideoGameOnlineShop.domain.Review;
import org.fasttrackit.VideoGameOnlineShop.persistance.ReviewRepository;
import org.fasttrackit.VideoGameOnlineShop.transfer.review.ReviewResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReviewService {
    private static final Logger LOGGER= LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
@Transactional
    public Page<ReviewResponse> getReviews(long productId, Pageable pageable){
        LOGGER.info("Retrieving reviews for product {}",productId);
        Page<Review> reviewsPage = reviewRepository.findByProductId(productId, pageable);

        List<ReviewResponse> reviewDtos=new ArrayList<>();
        for(Review review:reviewsPage.getContent()){
            ReviewResponse dto=new ReviewResponse();
            dto.setId(review.getId());
            dto.setContent(review.getContent());
            reviewDtos.add(dto);
        }

        return new PageImpl<>(reviewDtos,pageable,reviewsPage.getTotalElements());
    }
//        public void addProductsToCart(AddProductsToCartRequest request) {
//        LOGGER.info("Adding products to cart: {}", request);
//        Cart cart = cartRepository.findById(request.getCustomerId()).orElse(new Cart());
//        if (cart.getCustomer() == null) {
//            Customer customer = customerService.getCustomer(request.getCustomerId());
//            cart.setCustomer(customer);
//        }
//        for(Long id: request.getProductsIds())
//        {
//            Product product = productService.getProduct(id);
//            cart.addProductToCart(product);
//        }
//        cartRepository.save(cart);
//    }
//    de facut add review to product


}
