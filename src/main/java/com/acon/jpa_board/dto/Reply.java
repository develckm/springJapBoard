package com.acon.jpa_board.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

import lombok.Data;

/*
 *BOARD_SPRING.REPLY 
+-----------+--------------+------+-----+-------------------+-------------------+
| Field     | Type         | Null | Key | Default           | Extra             |
+-----------+--------------+------+-----+-------------------+-------------------+
| reply_no  | int          | NO   | PRI | NULL              | auto_increment    |
| title     | varchar(255) | NO   |     | NULL              |                   |
| contents  | varchar(255) | YES  |     |                   |                   |
| post_time | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
| img_path  | varchar(255) | YES  |     | NULL              |                   |
| good      | int          | NO   |     | 0                 |                   |
| bad       | int          | NO   |     | 0                 |                   |
| board_no  | int          | NO   | MUL | NULL              |                   |
| user_id   | varchar(255) | NO   | MUL | NULL              |                   |
+-----------+--------------+------+-----+-------------------+-------------------+
 *BOARD_SPRING.REPLY_PREFER
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| reply_prefer_no | int          | NO   | PRI | NULL    | auto_increment |
| prefer          | tinyint(1)   | YES  |     | NULL    |                |
| reply_no        | int          | NO   | MUL | NULL    |                |
| user_id         | varchar(255) | NO   | MUL | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
 * */
@Entity
@Data
public class Reply {
	@Id
	@GeneratedValue
	private int reply_no;  
	private String title;     
	private String contents;  
	private Date post_time;
	private String img_path;
	//@Formula : 해당 필드를 실제 db 칼럼이 아니라 서브쿼리로 불러오는 것으로 인지하게 됨
	@Formula("(SELECT COUNT(*) FROM REPLY_PREFER p WHERE p.prefer=true AND p.reply_no=reply_no )")
	private int good;      
	@Formula("(SELECT COUNT(*) FROM REPLY_PREFER p WHERE p.prefer=false AND p.reply_no=reply_no )")
	private int bad;      
	private int board_no;
	private String user_id;   
}
