package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
 

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
 // User  findByUsernameAndPassword(String username,String password);
  
 // @Query(value="")
 // User login(String name,String password);
	
	Optional<User>findByUsername(String username);
}
