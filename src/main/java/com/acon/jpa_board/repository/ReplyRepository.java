package com.acon.jpa_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acon.jpa_board.dto.Reply;
import com.acon.jpa_board.dto.ReplyPrefer;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
