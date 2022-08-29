package com.acon.jpa_board.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.acon.jpa_board.dto.BoardPrefer;
import com.acon.jpa_board.dto.Reply;
import com.acon.jpa_board.dto.ReplyPrefer;
import com.acon.jpa_board.dto.User;
import com.acon.jpa_board.repository.ReplyPreferRepository;
import com.acon.jpa_board.repository.ReplyRepository;
@RequestMapping("/reply")
@Controller
public class ReplyController {
	@Autowired
	ReplyRepository replyRepository;
	@Autowired
	ReplyPreferRepository replyPreferRepository;
	@PostMapping("/insert.do")
	public String insert (
			Reply reply,
			@SessionAttribute User loginUser) {
		Reply saveReply=replyRepository.save(reply);
		System.out.println(reply);
		return "redirect:/board/detail/"+reply.getBoardNo();
	}
	@GetMapping("/prefer/modify/{replyNo}/{prefer}")
	public @ResponseBody int  preferModify(
			ReplyPrefer prefer,
			@SessionAttribute User loginUser,
			HttpSession session) {
		int state=0;
		System.out.println(prefer);
		prefer.setUserId(loginUser.getUserId());
		Optional<ReplyPrefer>  preferOpt=replyPreferRepository.findByUserIdAndReplyNo(prefer.getUserId(), prefer.getReplyNo());
		try {
			if(preferOpt.isEmpty()) {
				replyPreferRepository.save(prefer); //insert
				state=1;
			}else {
				if(prefer.isPrefer()==preferOpt.get().isPrefer()) {//delete
					replyPreferRepository.deleteById(preferOpt.get().getReplyPreferNo());
					state=2;
				}else {
					prefer.setReplyPreferNo(preferOpt.get().getReplyPreferNo());
					replyPreferRepository.save(prefer); //update
					state=3;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			state=-1;
		}
		return state;
	}
	@GetMapping("/detail/{replyNo}")
	public String detail(
			@PathVariable int replyNo,	
			@SessionAttribute User loginUser,
			Model model) {
		Optional<Reply> replyOpt=replyRepository.findById(replyNo);
		model.addAttribute("reply", replyOpt.get());
		return "/board/replyDetail";
	}
	
	
}
