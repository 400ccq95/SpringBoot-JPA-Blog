package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
   @Autowired
	private UserRepository userReposity;
   
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id:"+user.getId());
		System.out.println("username"+user.getUsername());
		System.out.println("password"+user.getPassword());
		System.out.println("email"+user.getEmail());
		System.out.println("user"+user.getRole());
		System.out.println("createDate"+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userReposity.save(user);
		return "회원가입완료";
	}
}
