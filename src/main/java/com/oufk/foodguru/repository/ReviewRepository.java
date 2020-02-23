package com.oufk.foodguru.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oufk.foodguru.models.Review;

/*
 * Repository used to access Review data from MySQL database, with custom queries
 */

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	@Query("Select r From Review r WHERE r.user.id = :userId")
	List<Review> getAllFromUserId(@Param ("userId") long userId);

	@Query("Select r From Review r WHERE r.user.username = :username")
	List<Review> getAllFromUsername(@Param ("username") String username);
	
	@Query("Select r From Review r WHERE r.restaurant.id = :restaurantId")
	List<Review> getAllFromRestaurantId(@Param ("restaurantId") long id);
}
