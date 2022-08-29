package com.acon.jpa_board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acon.jpa_board.dto.Reply;
import com.acon.jpa_board.dto.ReplyPrefer;

@Repository
public interface ReplyPreferRepository extends JpaRepository<ReplyPrefer, Integer>{
	Optional<ReplyPrefer> findByUserIdAndReplyNo(String userId,int replyNo);
	
	
}
