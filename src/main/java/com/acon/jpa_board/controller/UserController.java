package com.acon.jpa_board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acon.jpa_board.dto.User;
import com.acon.jpa_board.repository.UserRepository;
@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/insert.do")
	public void insert() {}
	
	@PostMapping("/insert.do")
	public String insert(User user) {
		System.out.println(user);
		User saveUser=userRepository.save(user); 
		//save : user pk 로 검색했을 때 없으면 insert 있으면 update
		//save : user의 필드에 null 아닌 것만 저장 및 업데이트
		System.out.println(saveUser);
		return "redirect:/user/list/1";
	}
	
	
	@PostMapping("/update.do")
	public String update(User user) {
		User saveUser=userRepository.save(user);
		return "redirect:/user/list/1";
	}
	
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
