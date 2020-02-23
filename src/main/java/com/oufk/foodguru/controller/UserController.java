package com.oufk.foodguru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oufk.foodguru.exception.ResourceNotFoundException;
import com.oufk.foodguru.models.User;
import com.oufk.foodguru.payload.UserIdentityAvailability;
import com.oufk.foodguru.payload.UserProfile;
import com.oufk.foodguru.payload.UserSummary;
import com.oufk.foodguru.repository.UserRepository;
import com.oufk.foodguru.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/user/me")
    public UserSummary getCurrentUser(){
       UserPrincipal user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       UserSummary summary = new UserSummary(user.getId(), user.getUsername(), user.getName(), user.getEmail(), user.getAuthorities());
       return summary;
    }
	
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam (value = "username") String userName) {
    	Boolean isAvailable = !userRepo.existsByUsername(userName);
    	return new UserIdentityAvailability(isAvailable);
    }
    
    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam (value = "email") String email) {
        Boolean isAvailable = !userRepo.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);    	
    }
    
    @GetMapping("/users/{username}")
    public UserProfile getUserProfile(@PathVariable (value = "username") String username) {
    	User user = userRepo.findByUsername(username)
    			.orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    	UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());
        return userProfile;
    }
}
