package com.pawandootshop.pawandootshop.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class SecurityConfig {

	@Autowired
	UserDetailsService userdetailsservice;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtAuthenticationFilter filter;

	@Autowired
	JwtAuthenticationEntryPoint point;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable()).authorizeHttpRequests(

				auth -> auth.requestMatchers("/auth/**", "/Customerlogin/**","/signup/**").permitAll()
						.requestMatchers("/root/**", "/login/**", "/getallproduct/**").permitAll()
						.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();

	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}

	@Bean
	public DaoAuthenticationProvider DaoAuthenticaitonProvider() {

		DaoAuthenticationProvider daoprovider = new DaoAuthenticationProvider();
		daoprovider.setUserDetailsService(userdetailsservice);
		daoprovider.setPasswordEncoder(passwordEncoder);
		return daoprovider;

	}

}
