package com.oufk.foodguru.payload;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private String email;
	private String role;
    

	public UserSummary(Long id, String username, String name, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        for (GrantedAuthority authority : authorities) {
          if (authority.getAuthority().equals("ROLE_USER")) {
			this.role = "User";
			break;	
          } else {
			this.role = "Admin";
			break;
          }
        }
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getRole() {
        return role;
    }

}
