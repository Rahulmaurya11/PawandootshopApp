/*
 * package com.pawandootshop.pawandootshop.security;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfiguration;
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class webconfig extends websecu {
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception { http
 * .authorizeRequests() .antMatchers("/public/**").permitAll()
 * .anyRequest().authenticated() .and() .formLogin() .loginPage("/login")
 * .permitAll() .and() .logout() .permitAll(); }
 * 
 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
 * throws Exception { auth .inMemoryAuthentication()
 * .withUser("user").password("{noop}password").roles("USER");
 * 
 * } }
 */