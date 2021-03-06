package org.fasttrackit.VideoGameOnlineShop.persistance;

import org.fasttrackit.VideoGameOnlineShop.domain.Discount;
import org.fasttrackit.VideoGameOnlineShop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(String partialName, int minQuantity, Pageable pageable);

    Page<Product> findByGenreContaining(String genre, Pageable pageable);

    Page<Product> findByDiscount(Discount discount, Pageable pageable);

    Page<Product> findByAverageRatingGreaterThanEqual(double rating, Pageable pageable);


}
