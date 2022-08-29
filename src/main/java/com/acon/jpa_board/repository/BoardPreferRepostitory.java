package com.acon.jpa_board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acon.jpa_board.dto.BoardPrefer;

@Repository
public interface BoardPreferRepostitory extends JpaRepository<BoardPrefer, Integer>{
	Optional<BoardPrefer> findByUserIdAndBoardNo(String userId, int boardNo);
}
