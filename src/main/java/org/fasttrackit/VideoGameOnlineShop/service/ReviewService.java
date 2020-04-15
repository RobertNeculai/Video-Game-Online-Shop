package org.fasttrackit.VideoGameOnlineShop.service;

import org.fasttrackit.VideoGameOnlineShop.domain.Product;
import org.fasttrackit.VideoGameOnlineShop.domain.Review;
import org.fasttrackit.VideoGameOnlineShop.exception.ResourceNotFoundException;
import org.fasttrackit.VideoGameOnlineShop.persistance.ReviewRepository;
import org.fasttrackit.VideoGameOnlineShop.transfer.review.ReviewResponse;
import org.fasttrackit.VideoGameOnlineShop.transfer.review.SaveReviewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;


@Service
public class ReviewService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewService.class);

    private final ReviewRepository reviewRepository;
    private final ProductService productService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.productService = productService;
    }

    @Transactional
    public Page<ReviewResponse> getReviews(long productId, Pageable pageable) {
        LOGGER.info("Retrieving reviews for product {}", productId);
        Page<Review> reviewsPage = reviewRepository.findByProductId(productId, pageable);

        List<ReviewResponse> reviewDtos = new ArrayList<>();
        for (Review review : reviewsPage.getContent()) {
            ReviewResponse dto = new ReviewResponse();
            dto.setId(review.getId());
            dto.setContent(review.getContent());
            dto.setRating(review.getRating());
            reviewDtos.add(dto);
        }
        return new PageImpl<>(reviewDtos, pageable, reviewsPage.getTotalElements());
    }

    private ReviewResponse mapReviewResponse(Review review) {
        ReviewResponse reviewDto = new ReviewResponse();
        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setRating(review.getRating());
        return reviewDto;
    }

    @Transactional
    public void addReviewToProduct(long productId, SaveReviewRequest request) {
        LOGGER.info("Adding review to product: {}", productId);
        Review review = reviewRepository.findById(productId).orElse(new Review());

        if (review.getProduct() == null) {
            Product product = productService.findProduct(productId);

            review.setProduct(product);
        }
        review.setRating(request.getRating());
        review.setContent(request.getContent());
        reviewRepository.save(review);
        Product product = productService.findProduct(productId);
        product.setTotalRating(product.getTotalRating() + review.getRating());

        product.setAverageRating(refreshRating(productId));
    }

    private double refreshRating(long id) {
        Product product = productService.findProduct(id);
        int a = reviewRepository.findAllByProductId(id).size();
        double b = product.getTotalRating();
        return b / a;

    }

    @Transactional
    public void deleteReview(long id) {
        LOGGER.info("Deleting review {}", id);
        reviewRepository.deleteById(id);
    }

    // Update method checks Review Content, and updates only the newly modified proprieties
    @Transactional
    public ReviewResponse updateReview(long id, SaveReviewRequest request) {
        LOGGER.info("Updating product {}: {}", id, request);
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review " + id + " not found"));
        if (!request.getContent().equals("")) {
            if (!request.getContent().equals(review.getContent()) && request.getRating() != review.getRating())
                BeanUtils.copyProperties(request, review);
            else if (!request.getContent().equals(review.getContent()) && request.getRating() == review.getRating())
                review.setContent(request.getContent());
            else if (request.getContent().equals(review.getContent()) && request.getRating() != review.getRating())
                review.setRating(request.getRating());
            else
                LOGGER.info("Can't update with the same proprieties");
        } else if (request.getRating() != review.getRating())
            review.setRating(request.getRating());
        Review savedReview = reviewRepository.save(review);
        return mapReviewResponse(savedReview);
    }
}

