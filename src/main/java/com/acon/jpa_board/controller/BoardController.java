package com.acon.jpa_board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acon.jpa_board.dto.Board;
import com.acon.jpa_board.repository.BoardRepository;
@RequestMapping("/board")
@Controller
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/list/{page}")
	public String list(
			@PathVariable int page,
			Model model
			) {
		List<Board> boardList=boardRepository.findAll();
		model.addAttribute("boardList", boardList);
		return "/board/list";
	}
}






