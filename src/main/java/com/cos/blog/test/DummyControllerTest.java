package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;



//html 파일이 아니라 data를 리턴해주는 controller =  RestController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private UserRepository userRepository;
	
	//save 함수는 id를 전달하지 않으면 insert를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
	//save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 함.
	//email, password
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		 return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다";
		}

		return "삭제되었습니다. id:" +id;
		
	}
	
	@Transactional //Transactional 을 걸면 save 하지않아도 update가 된다
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {
		// JSON을 받으려면 RequestBody 어노테이션이 필요함
		System.out.println("id : "+ id);
		System.out.println("password : "+ requestUser.getPassword());
		System.out.println("email : "+ requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail()); 
		
		//userRepository.save(user);
		
		//더티 체킹
		return user;
	}
	
	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<User> list() {
		return userRepository.findAll();
	}
	
	//한 페이지당 2건의 데이터를 리턴받아 봄
	@GetMapping("/dummy/user")
	public Page<User> pageList(@PageableDefault(size=2,sort="id",direction=Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		// dummy test
		
		List<User> users = pagingUser.getContent();
		return pagingUser;
		
	}
	
	// {id} = 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id){
		// user/4 을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null 이 되니까
		// return 일때 null이 리턴이 된다. 그럼 프로그램에 문제가 생겨
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해! 
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 사용자가 없습니다.");
			}
		});
		// 요청: 웹브라우저   (웹브라우저는 html,js 만 이해함)
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
		// 스프링부트는 messageConverter가 응답시 자동작동함
		// 만약 자바 오브젝트를 return하게 되면 messageConverter가 jackon 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에 보냄
		return user;
	}
	// localhost8000/blog/dummy/join (요청) 
	// http의 body에 username,password,email 데이터를 가지고 ( 요청)
	@PostMapping("/dummy/join")
	public String join(User user) { // key=value (약속된 규칙)
		
		System.out.println("Id" + user.getId());
		System.out.println("username : " +user.getUsername());
		System.out.println("password : " +user.getPassword());
		System.out.println("email : " +user.getEmail());
		System.out.println("role : " +user.getRole());
		System.out.println("createDate : " +user.getCreateDate());
		
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다."; 
		
	}
}
