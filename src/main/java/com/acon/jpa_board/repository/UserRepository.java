package com.acon.jpa_board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acon.jpa_board.dto.User;

//mybatis의 mapper 인터페이스 역할 
//JpaRepository : entity(dto)를 기반으로 sql을 생성하기 때문에 꼭 dto와 pk의 제네릭으로 타입을 명시함
// JpaRepository : crud용 함수가 명시되어 있다. findAll,findeById,delete,save(insert,update) 
@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
}
