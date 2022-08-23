package com.acon.jpa_board.dto;
/*
+---------+--------------+------+-----+-------------------+-------------------+
| Field   | Type         | Null | Key | Default           | Extra             |
+---------+--------------+------+-----+-------------------+-------------------+
| userId | varchar(255) | NO   | PRI | NULL              |                   |
| name    | varchar(255) | NO   |     | NULL              |                   |
| pw      | varchar(255) | NO   |     | NULL              |                   |
| phone   | varchar(255) | NO   | UNI | NULL              |                   |
| email   | varchar(255) | NO   | UNI | NULL              |                   |
| birth   | date         | NO   |     | NULL              |                   |
| signup  | datetime     | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED |
+---------+--------------+------+-----+-------------------+-------------------+
 * 
 * */

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Data
@Entity //jpa에서 Entity로 사용할 dto를 의미
public class User {
	@Id //꼭 대표키를 명시해야 한다.(오류)
	@Column(name = "user_id")
	private String userId;
	private String name;
	private String pw;
	private String phone;
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@CreationTimestamp
	private Date signup;
	//FetchType.LAZY : 지연 로딩 (게으른 연산) ,view 에서  boards를 원하면 join을 한다.
	//실제 join을 하지 않고 해당 row에 다시 select를 하는 sub query 형태로 질의한다.
	//insertable = false, updatable = false : User의 필드가 아니기 때문에 insert,update 를 하지 않는다.(필수!!)
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private List<Board> boards;
	
	@OneToMany(fetch = FetchType.LAZY )
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private List<Reply> replies;
	
}
