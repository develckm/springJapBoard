package com.acon.jpa_board.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

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
		User saveUser=null;
		try {
			saveUser=userRepository.save(user); 
			//save : user pk 로 검색했을 때 없으면 insert 있으면 update
			//save : user의 필드에 null 아닌 것만 저장 및 업데이트
			System.out.println(saveUser);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(saveUser!=null) {
			return "redirect:/user/list/1";			
		}else {
			return "redirect:/user/insert.do";			
		}
	}
	
	
	@PostMapping("/update.do")
	public String update(User user) {
		User saveUser=null;
		try {
			saveUser=userRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(saveUser!=null) {
			return "redirect:/user/list/1";			
		}else {
			return "redirect:/user/detail/"+user.getUserId();			

		}
	}
	
	@GetMapping("/delete/{userId}")
	public String delete(@PathVariable String userId) {
		boolean del=false;
		try {
			userRepository.deleteById(userId);
			del=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(del) {
			return "redirect:/user/list/1";			
		}else {
			return "redirect:/user/detail/"+userId;			
		}
	}
	
	@GetMapping("/list/{page}")
	public String list(
			@PathVariable int page,			
			@RequestParam(defaultValue = "userId") String sort,
			@RequestParam(defaultValue = "desc") String direct,
			@RequestParam(defaultValue = "") String field,
			@RequestParam(defaultValue = "") String search,
			Model model) {
		int pageSize=5;
		Sort pageSort;
		if(direct.equals("desc")) {
			pageSort=Sort.by(sort).descending();
		}else {
			pageSort=Sort.by(sort).ascending();
		}
		Pageable pageable=PageRequest.of(page-1, pageSize, pageSort);
		Page<User> users=null;
		switch (field) {
			case "name":
				users=userRepository.findByNameContaining(search, pageable);
				break;
			case "userId":
				users=userRepository.findByUserIdContaining(search, pageable);
				break;
			case "email":
				users=userRepository.findByEmailContaining(search, pageable);
				break;
			case "birth":
				users=userRepository.findByBirthContaining(search, pageable);
				break;
			case "signup":
				users=userRepository.findBySignupContaining(search, pageable);
				break;
			default:
				users=userRepository.findAll(pageable);
		}
		model.addAttribute("users", users);
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
	@GetMapping("login.do")
	public void login(
			HttpServletRequest request,
			HttpSession session) {
		String prevPage=request.getHeader("Referer");//요청한 페이지의 이전 페이지(로그인하면 되돌아갈 페이지)
		session.setAttribute("redirectPage", prevPage);
	}
	@PostMapping("login.do")
	public String login(User user,
			@SessionAttribute(required = false) String redirectPage,
			HttpServletRequest request,
			HttpSession session) {
		System.out.println(user);
		Optional<User> loginUserOpt=userRepository.findByUserIdAndPw(user.getUserId(), user.getPw());
		
		if(loginUserOpt.isPresent()) {
			session.setAttribute("loginUser",loginUserOpt.get());
			if(redirectPage!=null) {
				return "redirect:"+redirectPage;
			}else {
				return "redirect:/";
			}
		}else {
			session.setAttribute("msg", "아이디와 비밀번호를 확인하세요!");
			return "redirect:/user/login.do";
		}
	}
}











