package com.acon.jpa_board.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
			Model model) {
		
		int pageSize=7;
		Pageable pageable=PageRequest.of(page-1, pageSize);
		Page<Board> boardList=boardRepository.findAll(pageable); //Page는 List에 page와 관련되 필드가 추가된 객체
		System.out.println(boardList.getNumber());//현제 페이지 
		System.out.println(boardList.getTotalPages());//전체 페이지 수
		System.out.println(boardList.getTotalElements());//전체 수
		System.out.println(boardList.hasNext()); //다음 페이지가 있나?
		System.out.println(boardList.hasPrevious()); //이전 페이지가 있나?

		model.addAttribute("boardList", boardList);
		return "/board/list";
	}
	@GetMapping("/detail/{boardNo}")
	public String detail(
			@PathVariable int boardNo,
			Model model) {
		Optional<Board> boardOpt=boardRepository.findById(boardNo);
		if(boardOpt.isPresent()) {
			model.addAttribute("board", boardOpt.get());
			return "/board/detail";
		}else {
			return "/board/list/1";
		}
		
	}
}






