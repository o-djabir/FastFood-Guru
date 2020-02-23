package com.oufk.foodguru.payload;

public class ReviewSummary {
	private long id;
	private String content;
	private String restaurantName;
	private int grade;
	private String author;

	public ReviewSummary(long id, String content, String restaurantName, int grade, String author) {
		this.id = id;
		this.content = content;
		this.restaurantName = restaurantName;
		this.grade = grade;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	
}
