package com.acon.jpa_board.controller;

import java.util.List;
import java.util.Optional;

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

import com.acon.jpa_board.dto.Board;
import com.acon.jpa_board.dto.User;
import com.acon.jpa_board.repository.BoardRepository;
@RequestMapping("/board")
@Controller
public class BoardController {
	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("/list/{page}")
	public String list(
			@PathVariable int page,
			@RequestParam(defaultValue = "boardNo") String sort,
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
		Page<Board> boardList=null;
		
		switch (field) {
			case "boardNo":				
				boardList=boardRepository.findByBoardNoContaining(search,pageable); 
				break;
			case "userId": 
				boardList=boardRepository.findByUser_UserIdContaining(search,pageable); 
				break;
			case "title": 
				boardList=boardRepository.findByTitleContaining(search,pageable); 
				break;
			case "contents": 
				boardList=boardRepository.findByContentsContaining(search,pageable); 
				break;
			case "postTime": 
				boardList=boardRepository.findByPostTimeContaining(search,pageable); 
				break;
			default: boardList=boardRepository.findAll(pageable);
		}

		System.out.println(boardList.getNumber());//현제 페이지 
		System.out.println(boardList.getTotalPages());//전체 페이지 수
		System.out.println(boardList.getTotalElements());//전체 수
		System.out.println(boardList.hasNext()); //다음 페이지가 있나?
		System.out.println(boardList.hasPrevious()); //이전 페이지가 있나?
		model.addAttribute("url", "/board/list/");
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
			return "redirect:/board/list/1";
		}	
	}
	@GetMapping("insert.do")
	public void insert(@SessionAttribute User loginUser) {}
	@PostMapping("insert.do")
	public String insert(
			@SessionAttribute User loginUser,
			Board board,
			HttpSession session) {
		Board saveBoard=null;
		board.setUser(loginUser);
		try {
			saveBoard=boardRepository.save(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(saveBoard!=null){
			return "redirect:/board/list/1";
		}else {
			return "redirect:/board/insert.do";

		}
	}
	@GetMapping("update/{boardNo}")
	public String update(
			@SessionAttribute User loginUser,
			@PathVariable int boardNo,
			Model model) {
		Optional<Board> boardOpt=boardRepository.findById(boardNo);
		if(boardOpt.isPresent()) {
			model.addAttribute("board", boardOpt.get());
			return "/board/update";
		}else {
			return "redirect:/board/detail/"+boardNo;
		}	
	
	}
	@PostMapping("update.do")
	public String update(
			@SessionAttribute User loginUser,
			Board board,
			HttpSession session) {
		Board saveBoard=null;
		board.setUser(loginUser);
		try {
			saveBoard=boardRepository.save(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(saveBoard!=null){
			return "redirect:/board/list/1";
		}else {
			return "redirect:/board/update/"+board.getBoardNo();

		}
	}

}

//
//<if test="search != null">
//	WHERE name LIKE CONCAT('%', #{search}, '%')
//</if>



