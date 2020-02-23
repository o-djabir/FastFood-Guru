package com.oufk.foodguru.models;

import javax.persistence.*;


@Entity
@Table (name= "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String content;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "restaurant_id", nullable = false)
	private FastFoodChain restaurant;
	
	private int grade;
	
	public Review() {
		
	}
	
	public Review(String content,User writer, FastFoodChain ffc, int grade) {
		this.content = content;
		this.user = writer;
		this.restaurant = ffc;
		this.grade = grade;
	}
	public long getID() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String newContent) {
		this.content = newContent; 
	}
	public User getAuthor() {
		return user;
	}
	public void setAuthor(User author) {
		this.user = author;
	}
	public FastFoodChain getFFC() {
		return restaurant;
	}
	public void setFFC(FastFoodChain f) {
		this.restaurant = f;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
