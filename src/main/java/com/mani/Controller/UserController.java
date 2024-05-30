package com.mani.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mani.Entity.User;
import com.mani.Service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService serv;
	
	@PostMapping("/add")
	public String addnew(@RequestBody User user) {
		return serv.addNewuser(user);
	}
	
	
	@GetMapping("/dev")
	public String getdev() {
		return "im dev";
	}
	@GetMapping("/prod")
	public String getadmin() {
		return "im admin";
	}
	
	
	
}
