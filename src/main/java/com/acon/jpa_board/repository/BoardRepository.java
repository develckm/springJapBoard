package com.acon.jpa_board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acon.jpa_board.dto.Board;
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	//select * from board where title='안녕';
	//select b from Board b where b.boardNo like %:boardNo%;
	@Query(value = "select b from Board b where CAST(b.boardNo as string) like %:boardNo%")
	Page<Board> findByBoardNoContaining(String boardNo, Pageable pageable);
	//select b from Board b where b.user.userId like '%:userId%';
	Page<Board> findByUser_UserIdContaining(String userId,Pageable pageable);
	Page<Board> findByTitleContaining(String title,Pageable pageable);
	Page<Board> findByContentsContaining(String contents,Pageable pageable);
	
	@Query(value = "select b from Board b where CAST(b.postTime as string) like %:postTime%")
	Page<Board> findByPostTimeContaining(String postTime,Pageable pageable);
}
