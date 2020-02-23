package com.oufk.foodguru.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oufk.foodguru.models.FastFoodChain;
import com.oufk.foodguru.models.Review;
import com.oufk.foodguru.payload.ApiResponse;
import com.oufk.foodguru.payload.FastFoodChainRequest;
import com.oufk.foodguru.payload.FastFoodChainSummary;
import com.oufk.foodguru.payload.ReviewSummary;
import com.oufk.foodguru.service.FastFoodChainService;
import com.oufk.foodguru.service.ReviewService;

@RestController
@RequestMapping("/api/restaurants")
public class FastFoodChainController {
	
	@Autowired
	private FastFoodChainService fastFoodChainService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/")
	public List<FastFoodChainSummary> getAllRestaurants() {
		List<FastFoodChainSummary> restaurants = new ArrayList<FastFoodChainSummary>();
		for (FastFoodChain f : fastFoodChainService.getAllRestaurants()) {
			restaurants.add(new FastFoodChainSummary(f.getName(), f.getId(), f.getAddress(), f.getLogo(), this.getAllReviewSummaries(f)));
		}
		return restaurants;
	}
	
	@GetMapping("/names")
	public ArrayList<String> getAllRestaurantsNames() {
		return fastFoodChainService.getAllRestaurantsNames();
	}
	
	@GetMapping("/{name}")
	public List<FastFoodChainSummary> getRestaurantByName(@PathVariable String name) {
		List<FastFoodChainSummary> restaurants = new ArrayList<FastFoodChainSummary>();
		FastFoodChain f = fastFoodChainService.getRestaurantByName(name);
		restaurants.add(new FastFoodChainSummary(f.getName(), f.getId(), f.getAddress(), f.getLogo(), getAllReviewSummaries(f)));
		return restaurants;
	}
	
	@PostMapping("/")
	public ResponseEntity<?> addRestaurant(@RequestBody FastFoodChainRequest request) {
		fastFoodChainService.createRestaurant(request.getName(), request.getAddress(), request.getLogo());
		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "Fast-Food chain restaurant created successfully!"));
	}
	
	public List<ReviewSummary> getAllReviewSummaries(FastFoodChain f) {
		List<ReviewSummary> allReviews = new ArrayList<ReviewSummary>();
		 for (Review r : reviewService.getReviewsFromRestaurantId(f.getId())) {
			allReviews.add(new ReviewSummary(r.getID(), r.getContent(), r.getFFC().getName(), r.getGrade(), r.getAuthor().getName()));
		 }
		 return allReviews;
	}
}
