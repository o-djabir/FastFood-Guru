package com.oufk.foodguru.models;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name="restaurants", uniqueConstraints = {
		@UniqueConstraint(columnNames = {
				"name"
		}),
		@UniqueConstraint(columnNames = {
				"address"
		})
})
public class FastFoodChain {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@Size(max = 40)
	private String name;
	
	@NotBlank
	@Size(max = 150)
	private String address;
	
	@Size(max = 150)
	private String logo;
	
	@OneToMany(
			mappedBy="restaurant",
			cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
	)
	private Set<Review> reviews = new HashSet<Review>();
	
	public FastFoodChain() {
		
	}
	
	public FastFoodChain(String name, String address) {
		this.address = address;
		this.name = name;
	}
	
    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
 
    public List<Review> getAllReviews() {
    	List<Review> r = new ArrayList<Review>();
    	for (Review review : this.reviews) {
			r.add(review);
		}
    	return r;
    }
    
    public Review getReviewFromSpecificUser (User writer) {
    	boolean b = false;
    	Review r = null;
    	for (Review review : reviews) {
			if (review.getAuthor().equals(writer)) {
				b = true;
				r = review;
			}
    	}	
		if (!b) throw new IllegalArgumentException("Couldn't find the review"); 
		return r;	
    }
 
    public void addReview (Review review) {
    	reviews.add(review);
    }
}
