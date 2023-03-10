package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
// 404 error = 찾지못했다
	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() { //파일을 리턴함
		System.out.println("tempHome()");
		
		//파일 리턴 기본경로 : src/main/resources/static
		//리턴명을 : /home.html , 슬래쉬를 붙여야함
		//풀경로 : src/main/resources/static/home.html
		//RestController - 문자열 자체를 리턴
		// Controller - 해당 경로에있는 파일을 리턴
		//jsp파일 = 동적인파일
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp") 
	public String tempJsp() {
		
		 // prefix : /WEB-INF/views/
		// suffix: .jsp
		// 풀경로 : WEB_INF/views/test.jsp
		return "test";
	}
	
	
	
}
