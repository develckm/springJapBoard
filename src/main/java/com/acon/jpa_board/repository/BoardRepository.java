package com.acon.jpa_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acon.jpa_board.dto.Board;
@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
