package org.fasttrackit.VideoGameOnlineShop.persistance;

import org.fasttrackit.VideoGameOnlineShop.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    //find reviews by nested property( id property of product )
    Page<Review> findByProductId(long productId, Pageable pageable);

   // Page<Review>findByRating(long productId,int rating, Pageable pageable);

}
