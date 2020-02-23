package com.oufk.foodguru.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oufk.foodguru.models.Review;
import com.oufk.foodguru.payload.ApiResponse;
import com.oufk.foodguru.payload.ReviewSummary;
import com.oufk.foodguru.payload.SubmittedReview;
import com.oufk.foodguru.repository.FastFoodChainRepository;
import com.oufk.foodguru.repository.ReviewRepository;
import com.oufk.foodguru.repository.UserRepository;
import com.oufk.foodguru.security.UserPrincipal;
import com.oufk.foodguru.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	 @Autowired
	 UserRepository userRepo;
	 
	 @Autowired
	 ReviewRepository reviewRepo;

	 @Autowired
	 FastFoodChainRepository ffcRepo;
	
	 @Autowired
	 ReviewService reviewService;
	 
	 @GetMapping("/")
	 public List<ReviewSummary> showAllReviews() {
		 List<ReviewSummary> allReviews = new ArrayList<ReviewSummary>();
		 for (Review r : reviewRepo.findAll()) {
			allReviews.add(new ReviewSummary(r.getID(), r.getContent(), r.getFFC().getName(), r.getGrade(), r.getAuthor().getName()));
		}
		 return allReviews;
	 }
	 
	 @GetMapping("/users/{username}")
	 public List<ReviewSummary> showAllUserReviews(@PathVariable (value="username") String username) {
		 List<ReviewSummary> allReviews = new ArrayList<ReviewSummary>();
		 for (Review r : reviewService.getReviewsFromUsername(username)) {
			allReviews.add(new ReviewSummary(r.getID(), r.getContent(), r.getFFC().getName(), r.getGrade(), r.getAuthor().getName()));
		}
		 return allReviews;
	 }
	 
	 @GetMapping("/restaurants/{id}")
	 public List<ReviewSummary> showAllRestaurantReviews(@PathVariable (value="id") long id) {
		 List<ReviewSummary> allReviews = new ArrayList<ReviewSummary>();
		 for (Review r : reviewService.getReviewsFromRestaurantId(id)) {
			allReviews.add(new ReviewSummary(r.getID(), r.getContent(), r.getFFC().getName(), r.getGrade(), r.getAuthor().getName()));
		 }
		 return allReviews;
	 }
	 
	 @PostMapping("/")
	 public ResponseEntity<?> addReview(@RequestBody SubmittedReview submittedReview) {
		 UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 reviewService.createReview(submittedReview, currentUser);
		 return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Review created successfully by "+currentUser.getUsername()+"!"));
	 }
	 
}
