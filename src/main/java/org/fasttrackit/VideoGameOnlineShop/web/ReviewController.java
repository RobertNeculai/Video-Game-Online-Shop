package org.fasttrackit.VideoGameOnlineShop.web;

import org.fasttrackit.VideoGameOnlineShop.service.ReviewService;
import org.fasttrackit.VideoGameOnlineShop.transfer.review.ReviewResponse;
import org.fasttrackit.VideoGameOnlineShop.transfer.review.SaveReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;




    @CrossOrigin
    @RequestMapping("/reviews")
    @RestController
    public class ReviewController {
        private final ReviewService reviewService;
        @Autowired
        public ReviewController(ReviewService reviewService) {
            this.reviewService = reviewService;
        }
        @PostMapping("/{id}")
        public ResponseEntity<Void> addReviewToProduct(@PathVariable long id,@Valid @RequestBody SaveReviewRequest request){
            reviewService.addReviewToProduct(id,request);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        @GetMapping("/{id}")
        public ResponseEntity<Page<ReviewResponse>> getReview(@PathVariable("id") long productId, Pageable pageable){
            Page<ReviewResponse> reviewResponsePage = reviewService.getReviews(productId,pageable);
            return new ResponseEntity<>(reviewResponsePage,HttpStatus.OK);
        }
        @PutMapping("/{id}")
        public ResponseEntity<ReviewResponse> updateReview(@PathVariable("id") long reviewId,@Valid @RequestBody SaveReviewRequest request) {
            ReviewResponse response=reviewService.updateReview(reviewId, request);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteReview(@PathVariable("id") long id){
            reviewService.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }
