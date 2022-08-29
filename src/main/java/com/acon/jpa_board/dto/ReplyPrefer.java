package com.acon.jpa_board.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
/*
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| reply_prefer_no | int          | NO   | PRI | NULL    | auto_increment |
| reply_no        | int          | NO   | MUL | NULL    |                |
| prefer          | tinyint(1)   | YES  |     | NULL    |                |
| user_id         | varchar(255) | NO   | MUL | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
 * 
 * */
@Data
@Table(	name = "reply_prefer",
		uniqueConstraints = @UniqueConstraint(columnNames = {"reply_no","user_id"}))
@Entity
public class ReplyPrefer {
	@Id
	@Column(name="reply_prefer_no")
	private int replyPreferNo;

	@Column(name="reply_no")
	private int replyNo;
	@Column(name="user_id")
	private String userId;
	private boolean prefer;

}
