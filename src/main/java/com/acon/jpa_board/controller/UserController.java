package com.acon.jpa_board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acon.jpa_board.dto.User;
import com.acon.jpa_board.repository.UserRepository;
@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/list/{page}")
	public String list(
			@PathVariable int page,
			Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	@GetMapping("/detail/{userId}")
	public String detail(
			@PathVariable String userId,
			Model model) {
		Optional<User> user_opt=userRepository.findById(userId);
		if(user_opt.isPresent()) {
			model.addAttribute("user", user_opt.get());	
		}
		return "/user/detail";
	}
}
