package com.acon.jpa_board.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Formula;

import lombok.Data;

/*
*BOARD_SPRING.BOARD
+-----------+--------------+------+-----+-------------------+-------------------+
| Field     | Type         | Null | Key | Default           | Extra             |
+-----------+--------------+------+-----+-------------------+-------------------+
| boardNo  | int          | NO   | PRI | NULL              | auto_increment    |
| title     | varchar(255) | NO   |     | NULL              |                   |
| contents  | varchar(255) | YES  |     |                   |                   |
| postTime | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| userId   | varchar(255) | NO   | MUL | NULL              |                   |
| good      | int          | NO   |     | 0                 |                   |
| bad       | int          | NO   |     | 0                 |                   |
| views     | int          | NO   |     | 0                 |                   |
+-----------+--------------+------+-----+-------------------+-------------------+
*BOARD_SPRING.BOARD_PREFER
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| board_prefer_no | int          | NO   | PRI | NULL    | auto_increment |
| boardNo        | int          | NO   | MUL | NULL    |                |
| prefer          | tinyint(1)   | YES  |     | NULL    |                |
| userId         | varchar(255) | NO   | MUL | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
*
*/
@Entity
@Data
public class Board {
	
	//dto Entity의 필드명을 _ 를 포함시키면 안된다. jpa _를 필드로 인지 한다. userId == user.id, boardNo==board.no
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //auto_increment :insert 시 자동으로 생성되는 pk 
	@Column(name="board_no")
	private int boardNo; 
	
	private String title;    
	private String contents; 
	private Date postTime;
	
	//private String userId; //n:1 1:1 join 의 경우는 주체가 되는 테이블에 fk가 존재한다.  
	//fk가 user 필드 내부에 에 존재한다고 명시해야 한다. 
	@ManyToOne //유저1명이 여러개의 게시글을 작성
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	
	@OneToMany //보드 1개에 여러개의 댓글을 작성
	@JoinColumn(name = "board_no",insertable = false,updatable = false)
	private List<Reply> replies;
	@Formula("(SELECT COUNT(*) FROM BOARD_PREFER p WHERE p.prefer=true AND p.board_no=board_no)")
	private int good;     
	@Formula("(SELECT COUNT(*) FROM BOARD_PREFER p WHERE p.prefer=false AND p.board_no=board_no)")
	private int bad;    
	private int views;    
	
}	 