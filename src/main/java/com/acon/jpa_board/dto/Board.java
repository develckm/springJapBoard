package com.acon.jpa_board.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

/*
+-----------+--------------+------+-----+-------------------+-------------------+
| Field     | Type         | Null | Key | Default           | Extra             |
+-----------+--------------+------+-----+-------------------+-------------------+
| board_no  | int          | NO   | PRI | NULL              | auto_increment    |
| title     | varchar(255) | NO   |     | NULL              |                   |
| contents  | varchar(255) | YES  |     |                   |                   |
| post_time | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| user_id   | varchar(255) | NO   | MUL | NULL              |                   |
| good      | int          | NO   |     | 0                 |                   |
| bad       | int          | NO   |     | 0                 |                   |
| views     | int          | NO   |     | 0                 |                   |
+-----------+--------------+------+-----+-------------------+-------------------+*/
@Entity
@Data
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //auto_increment :insert 시 자동으로 생성되는 pk 
	private int board_no; 
	private String title;    
	private String contents; 
	private Date post_time;
	
	//private String user_id; //n:1 1:1 join 의 경우는 주체가 되는 테이블에 fk가 존재한다.  
	//fk가 user 필드 내부에 에 존재한다고 명시해야 한다. 
	@ManyToOne //유저1명이 여러개의 게시글을 작성
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private User user;
	
	@OneToMany //보드 1개에 여러개의 댓글을 작성
	@JoinColumn(name = "board_no",insertable = false,updatable = false)
	private List<Reply> replies;
	
	
	
	private int good;     
	private int bad;    
	private int views;    
}






