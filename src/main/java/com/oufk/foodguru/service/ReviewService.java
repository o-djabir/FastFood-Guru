package com.oufk.foodguru.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oufk.foodguru.exception.ResourceNotFoundException;
import com.oufk.foodguru.models.FastFoodChain;
import com.oufk.foodguru.models.Review;
import com.oufk.foodguru.payload.SubmittedReview;
import com.oufk.foodguru.repository.FastFoodChainRepository;
import com.oufk.foodguru.repository.ReviewRepository;
import com.oufk.foodguru.repository.UserRepository;
import com.oufk.foodguru.security.UserPrincipal;


/*
 * * This class uses the Review repository to retrieve and add the needed data from and to the database. It will interact directly with the controller
 */
@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepo;
	@Autowired
	FastFoodChainRepository fastFoodChainRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public Review createReview(SubmittedReview submittedReview, UserPrincipal currentUser) {
		Review review = new Review();
		review.setAuthor(userRepo.findById(currentUser.getId()).get());
		review.setContent(submittedReview.getContent());
		review.setFFC(fastFoodChainRepo.findById(submittedReview.getFfcId()).get());
		review.setGrade(submittedReview.getGrade());
		fastFoodChainRepo.findById(submittedReview.getFfcId()).get().addReview(review);
		return reviewRepo.save(review);
	}
	public List<Review> getReviewsFromUserId(UserPrincipal currentUser) {
		List<Review> all = new ArrayList<Review>();
		reviewRepo.getAllFromUserId(userRepo.findById(currentUser.getId()).get().getId()).forEach(review -> all.add(review) );
		return all;
	}
	public List<Review> getReviewsFromUsername(String username) {
		List<Review> all = new ArrayList<Review>();
		reviewRepo.getAllFromUsername(userRepo.findByUsername(username).get().getUsername()).forEach(review -> all.add(review));
		return all;
	}
	public List<Review> getReviewsFromRestaurantId(long id) {
		List<Review> all = new ArrayList<Review>();
		reviewRepo.getAllFromRestaurantId(fastFoodChainRepo.findById(id).get().getId()).forEach(review -> all.add(review) );
		return all;
	}
	public List<Review> getReviewsFromRestaurantName(String name){
		FastFoodChain chain = fastFoodChainRepo.findByName(name).orElseThrow(() -> new ResourceNotFoundException("Restaurant", "Name", name));
		List<Review> all = new ArrayList<Review>();
		reviewRepo.getAllFromRestaurantId(chain.getId()).forEach(review -> all.add(review));
		return all;
	}
	
}
