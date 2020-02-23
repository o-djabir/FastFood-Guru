package com.oufk.foodguru.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oufk.foodguru.payload.ApiResponse;
import com.oufk.foodguru.security.CustomUserDetailsService;
import com.oufk.foodguru.security.JwtAuthenticationEntryPoint;
import com.oufk.foodguru.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFiler() {
		return new JwtAuthenticationFilter();
	}
	
	@Bean
	public SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter() {
	    return new SecurityContextHolderAwareRequestFilter();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder
			.userDetailsService(customUserDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        	.cors()
            	.and()
            .csrf()
            	.disable()
            .exceptionHandling()
            	.authenticationEntryPoint(unauthorizedHandler)
            	.and()
            .logout()
            	.logoutUrl("/api/signout")
            	.logoutSuccessHandler(new AuthentificationLogoutSuccessHandler())
            	.invalidateHttpSession(true)
            	.and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
        .authorizeRequests()
            .antMatchers("/",
                "/favicon.ico",
                "/**/*.png",
                "/**/*.gif",
                "/**/*.svg",
                "/**/*.jpg",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js")
                .permitAll()
            .antMatchers("/api/auth/**")
                .permitAll()
            .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                .permitAll()
            .antMatchers(HttpMethod.GET, "/api/restaurants/**", "/api/users/**", "/api/reviews/**")
                .permitAll()
            .anyRequest()
                .authenticated();
		
		http.addFilterBefore(jwtAuthenticationFiler(), UsernamePasswordAuthenticationFilter.class);
	}
	private class AuthentificationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    	private ObjectMapper mapper = new ObjectMapper();
    	
        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException, ServletException {
            response.setStatus(HttpServletResponse.SC_OK);
            PrintWriter out = response.getWriter();
            String jsonString = mapper.writeValueAsString(new ApiResponse(true, "User successfully logged out!"));
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            out.print(jsonString);
            out.flush();
        }
    }
}

