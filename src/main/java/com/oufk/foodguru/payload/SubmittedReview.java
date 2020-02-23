package com.oufk.foodguru.payload;
import javax.validation.constraints.*;

public class SubmittedReview {
	@NotBlank
	private String content;
	
	private long ffcId;
	
	private int grade;
	
	public String getContent() {
		return this.content;
	}
	public int getGrade() {
		return this.grade;
	}
	public long getFfcId() {
		return this.ffcId;
	}
	
}
