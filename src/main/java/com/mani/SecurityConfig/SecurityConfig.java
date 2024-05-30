package com.mani.SecurityConfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.mani.Entity.User;
import com.mani.Repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	UserRepository repo;

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		List<User> user = repo.findAll();
		for (User u : user) {
			auth.inMemoryAuthentication().withUser(u.getUsername()).password(u.getPassword()).roles(u.getRoles());

		}

	}

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/users/add").permitAll();

		http.authorizeRequests().antMatchers("/users/prod").hasRole("admin");

		http.authorizeRequests().antMatchers("/users/dev").hasAnyRole("user").and().formLogin().and().logout()
				.permitAll();
	}

}
