package com.acon.jpa_board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acon.jpa_board.dto.User;

//mybatis의 mapper 인터페이스 역할 
//JpaRepository : entity(dto)를 기반으로 sql을 생성하기 때문에 꼭 dto와 pk의 제네릭으로 타입을 명시함
// JpaRepository : crud용 함수가 명시되어 있다. findAll,findeById,delete,save(insert,update) 
@Repository
public interface UserRepository extends JpaRepository<User, String>{
	Optional<User> findByUserIdAndPw (String userId,String pw); 
	//Select u from User u where userId=:userId And pw=:pw 
	
	Page<User>findByNameContaining(String name, Pageable pagebale); 
	//Select u from User u where name like %:name%
	Page<User>findByUserIdContaining(String userId, Pageable pagebale); 
	Page<User>findByEmailContaining(String email, Pageable pagebale); 
	@Query(value = "select u from User u where CAST(u.birth as string) like %:birth%")
	Page<User>findByBirthContaining(String birth, Pageable pagebale); 
	@Query(value = "select u from User u where CAST(u.signup as string) like %:signup%")
	Page<User>findBySignupContaining(String signup, Pageable pagebale); 

}
