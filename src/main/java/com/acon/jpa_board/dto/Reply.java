package com.acon.jpa_board.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

import lombok.Data;

/*
 *BOARD_SPRING.REPLY 
+-----------+--------------+------+-----+-------------------+-------------------+
| Field     | Type         | Null | Key | Default           | Extra             |
+-----------+--------------+------+-----+-------------------+-------------------+
| replyNo  | int          | NO   | PRI | NULL              | auto_increment    |
| title     | varchar(255) | NO   |     | NULL              |                   |
| contents  | varchar(255) | YES  |     |                   |                   |
| postTime | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| imgPath  | varchar(255) | YES  |     | NULL              |                   |
| good      | int          | NO   |     | 0                 |                   |
| bad       | int          | NO   |     | 0                 |                   |
| boardNo  | int          | NO   | MUL | NULL              |                   |
| userId   | varchar(255) | NO   | MUL | NULL              |                   |
+-----------+--------------+------+-----+-------------------+-------------------+
 * */
@Entity
@Data
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reply_no")
	private int replyNo;  
	private String title;     
	private String contents; 
	@Column(name = "post_time")
	private Date postTime;
	@Column(name = "img_path")
	private String imgPath;
	//@Formula : 해당 필드를 실제 db 칼럼이 아니라 서브쿼리로 불러오는 것으로 인지하게 됨
	@Formula("(SELECT COUNT(*) FROM REPLY_PREFER p WHERE p.prefer=true AND p.reply_no=reply_no )")
	private int good;      
	@Formula("(SELECT COUNT(*) FROM REPLY_PREFER p WHERE p.prefer=false AND p.reply_no=reply_no )")
	private int bad;
	@Column(name = "board_no")
	private int boardNo;
	@ManyToOne //유저1명이 게시글에 여러 댓글 작성
	@JoinColumn(name = "user_id") //user_id는 등록 수정 되는 fk
	private User user;
	@Transient //사용하지 않는 필드 선언
	ReplyPrefer lgoinPrefer;
}
