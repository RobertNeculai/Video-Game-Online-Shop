package org.fasttrackit.VideoGameOnlineShop.persistance;

import org.fasttrackit.VideoGameOnlineShop.domain.Product;
import org.fasttrackit.VideoGameOnlineShop.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    Page<Product>findByRating(long rating,Pageable pageable);
    Page<Review> findByProductId(long productId, Pageable pageable);


}
