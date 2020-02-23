package com.oufk.foodguru.payload;

import java.util.*;

public class FastFoodChainSummary {
	private String name;
	private long id;
	private String address;
	private String logo;
	

	private List<ReviewSummary> reviews = new ArrayList<ReviewSummary>();
	
	public FastFoodChainSummary(String name, long id, String address, String logo, List<ReviewSummary> reviews) {
		this.name = name;
		this.id = id;
		this.address = address;
		this.logo = logo;
		this.reviews = reviews;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<ReviewSummary> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewSummary> reviews) {
		this.reviews = reviews;
	}
	
	
	
}
