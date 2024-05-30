package com.mani.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mani.Entity.User;
import com.mani.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;

	public String addNewuser(User user) {
		
		User use=new User();
		use.setUsername(user.getUsername());
		use.setEmail(user.getEmail());
		use.setPassword(encoder.encode(user.getPassword()));
		System.out.println(user.getRoles());
		use.setRoles(user.getRoles());
		repo.save(use);
		return "saved";
	}

}
