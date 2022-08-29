package com.acon.jpa_board.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;
/*
 * board_no+user_id =UK
+-----------------+--------------+------+-----+---------+----------------+
| Field           | Type         | Null | Key | Default | Extra          |
+-----------------+--------------+------+-----+---------+----------------+
| board_prefer_no | int          | NO   | PRI | NULL    | auto_increment |
| board_no        | int          | NO   | MUL | NULL    |                |
| prefer          | tinyint(1)   | YES  |     | NULL    |                |
| user_id         | varchar(255) | NO   | MUL | NULL    |                |
+-----------------+--------------+------+-----+---------+----------------+
 * */
@Data
@Table(	name = "board_prefer",
		uniqueConstraints = @UniqueConstraint(columnNames = {"board_no","user_id"}))
@Entity
public class BoardPrefer {
	@Id
	@Column(name="board_prefer_no")
	private int boardPreferNo;

	@Column(name="board_no")
	private int boardNo;
	@Column(name="user_id")
	private String userId;
	private boolean prefer;
}
